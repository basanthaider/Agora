package com.example.Diva.domain.product.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class ProductVariantResponseDto {
    private Long id;
    private String sku;
    private Double price;
    private Integer stockQuantity;
    private String colorName;
    private String sizeName;
    private List<String> imageUrls;
}
