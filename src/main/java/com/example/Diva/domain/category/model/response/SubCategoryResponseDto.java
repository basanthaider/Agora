package com.example.Diva.domain.category.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubCategoryResponseDto {
    private Long id;
    private String name;
    private Long parentCategoryId;
    private String parentCategoryName;
}
