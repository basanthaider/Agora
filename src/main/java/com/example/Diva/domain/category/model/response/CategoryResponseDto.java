package com.example.Diva.domain.category.model.response;

import com.example.Diva.utill.BaseEnums;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private List<SubCategoryResponseDto> subCategories;
}
