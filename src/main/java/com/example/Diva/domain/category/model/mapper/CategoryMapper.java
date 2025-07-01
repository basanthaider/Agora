package com.example.Diva.domain.category.model.mapper;

import com.example.Diva.domain.category.model.request.CategoryRequestDto;
import com.example.Diva.domain.category.model.request.SubCategoryRequestDto;
import com.example.Diva.domain.category.model.response.CategoryResponseDto;
import com.example.Diva.domain.category.model.response.SubCategoryResponseDto;
import com.example.Diva.entity.Category;
import com.example.Diva.entity.SubCategory;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public class CategoryMapper {
    public static Category toEntity(CategoryRequestDto dto) {
        return Category
                .builder()
                .name(dto.getName())
                .build();

    }

    public static CategoryResponseDto toResponse(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .subCategories(
                        category.getSubCategories() != null
                                ? category.getSubCategories()
                                .stream()
                                .map(CategoryMapper::toSubCategoryResponse)
                                .collect(Collectors.toList())
                                : List.of()
                )
                .build();
    }

    public static SubCategory toSubCategory(SubCategoryRequestDto dto, Category category) {
       return SubCategory.builder()
                .name(dto.getName())
                .category(category)
                .build();

    }
    public static SubCategoryResponseDto toSubCategoryResponse(SubCategory subCategory) {
        return SubCategoryResponseDto.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .parentCategoryId(subCategory.getCategory().getId())
                .parentCategoryName(subCategory.getCategory().getName())
                .build();
    }


}
