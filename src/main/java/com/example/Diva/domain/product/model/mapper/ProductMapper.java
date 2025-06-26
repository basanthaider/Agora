package com.example.Diva.domain.product.model.mapper;

import com.example.Diva.domain.product.model.request.ProductRequestDto;
import com.example.Diva.domain.product.model.response.ProductResponseDto;
import com.example.Diva.entity.Brand;
import com.example.Diva.entity.Category;
import com.example.Diva.entity.Product;

public class ProductMapper {
    public static Product toEntity(ProductRequestDto dto, Category category, Brand brand) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(category)
                .brand(brand)
                .build();
    }

    public static ProductResponseDto toResponse(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .brandName(product.getBrand().getName())
                .categoryName(product.getCategory().getName())
                .variants(product.getVariants()
                        .stream()
                        .map(ProductVariantMapper::toResponse)
                        .toList())
                .build();
    }
}
