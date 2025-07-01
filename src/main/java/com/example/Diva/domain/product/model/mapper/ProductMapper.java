package com.example.Diva.domain.product.model.mapper;

import com.example.Diva.domain.product.model.request.ProductRequestDto;
import com.example.Diva.domain.product.model.response.ProductResponseDto;
import com.example.Diva.entity.Brand;
import com.example.Diva.entity.Product;
import com.example.Diva.entity.SubCategory;

public class ProductMapper {
    public static Product toEntity(ProductRequestDto dto, SubCategory subCategory, Brand brand) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .subcategory(subCategory)
                .brand(brand)
                .build();
    }

    public static ProductResponseDto toResponse(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .brandName(product.getBrand().getName())
                .categoryName(product.getSubcategory().getName())
                .variants(product.getVariants()
                        .stream()
                        .map(ProductVariantMapper::toResponse)
                        .toList())
                .build();
    }
}
