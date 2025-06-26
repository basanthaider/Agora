package com.example.Diva.domain.product.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {
    private String name;
    private String description;
    private Long categoryId;
    private Long brandId;
    private List<ProductVariantRequestDto> variants;
}
