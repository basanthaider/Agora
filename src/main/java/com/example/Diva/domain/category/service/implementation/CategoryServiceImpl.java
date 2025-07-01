package com.example.Diva.domain.category.service.implementation;

import com.example.Diva.domain.category.model.mapper.CategoryMapper;
import com.example.Diva.domain.category.model.request.CategoryRequestDto;
import com.example.Diva.domain.category.model.request.SubCategoryRequestDto;
import com.example.Diva.domain.category.model.response.CategoryResponseDto;
import com.example.Diva.domain.category.model.response.SubCategoryResponseDto;
import com.example.Diva.domain.category.service.contractor.CategoryService;
import com.example.Diva.entity.Category;
import com.example.Diva.entity.SubCategory;
import com.example.Diva.repository.CategoryRepository;
import com.example.Diva.repository.SubCategoryRepository;
import com.example.Diva.utill.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private  SubCategoryRepository subCategoryRepository;

    @Override
    public ResponseEntity<Object> createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = CategoryMapper.toEntity(categoryRequestDto);
        categoryRepository.save(category);
        CategoryResponseDto categoryResponseDto = CategoryMapper.toResponse(category);
        return new ResponseEntity<>(new BaseResponse<>(true, "Category created successfully",  categoryResponseDto), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> addSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        Category category = categoryRepository.findById(subCategoryRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        SubCategory sub = CategoryMapper.toSubCategory(subCategoryRequestDto, category);
         subCategoryRepository.save(sub);
         SubCategoryResponseDto subCategoryResponseDto = CategoryMapper.toSubCategoryResponse(sub);
        return new ResponseEntity<>(new BaseResponse<>(true, "Subcategory added successfully",subCategoryResponseDto ), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = categories.stream()
                .map(CategoryMapper::toResponse)
                .toList();
        if (categoryResponseDtos.isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(false, "No categories found", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new BaseResponse<>(true, "Categories retrieved successfully", categoryResponseDtos), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getSubcategoriesByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        List<SubCategory> subCategories = subCategoryRepository.findByCategoryId(categoryId);
        if (subCategories.isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(false, "No subcategories found for this category", null), HttpStatus.NOT_FOUND);
        }
        List<SubCategoryResponseDto> subCategoryResponseDtos = subCategories.stream()
                .map(CategoryMapper::toSubCategoryResponse)
                .toList();
        return new ResponseEntity<>(new BaseResponse<>(true, "Subcategories retrieved successfully", subCategoryResponseDtos), HttpStatus.OK);
    }
}
