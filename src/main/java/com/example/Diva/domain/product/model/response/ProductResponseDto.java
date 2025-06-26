package com.example.Diva.domain.product.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private String categoryName;
    private String brandName;
    private List<ProductVariantResponseDto> variants;
}
