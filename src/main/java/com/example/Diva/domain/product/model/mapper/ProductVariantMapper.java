package com.example.Diva.domain.product.model.mapper;

import com.example.Diva.domain.product.model.request.ColorRequestDto;
import com.example.Diva.domain.product.model.request.ProductVariantRequestDto;
import com.example.Diva.domain.product.model.request.SizeRequestDto;
import com.example.Diva.domain.product.model.response.ColorResponseDto;
import com.example.Diva.domain.product.model.response.ProductVariantResponseDto;
import com.example.Diva.domain.product.model.response.SizeResponseDto;
import com.example.Diva.entity.*;

import java.util.stream.Collectors;

public class ProductVariantMapper {
    public static ProductVariant toEntity(
            ProductVariantRequestDto dto,
            Product product,
            Color color,
            Size size
    ) {
        return ProductVariant.builder()
                .product(product)
                .color(color)
                .size(size)
                .price(dto.getPrice())
                .stockQuantity(dto.getStockQuantity())
                .sku(dto.getSku())
                .images(
                        dto.getImageUrls().stream()
                                .map(url -> ProductImage.builder()
                                        .imageUrl(url)
                                        .variant(null) // Will be set later
                                        .build())
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static ProductVariantResponseDto toResponse(ProductVariant variant) {
        return ProductVariantResponseDto.builder()
                .id(variant.getId())
                .colorName(variant.getColor().getName())
                .sizeName(variant.getSize().getName())
                .price(variant.getPrice())
                .stockQuantity(variant.getStockQuantity())
                .sku(variant.getSku())
                .imageUrls(variant.getImages().stream()
                        .map(ProductImage::getImageUrl)
                        .toList())
                .build();
    }
    public static Color toEntity(ColorRequestDto dto){
        return Color.builder()
                .name(dto.getName())
                .hexCode(dto.getHexCode())
                .build();
    }
    public static ColorResponseDto toResponse(Color color){
        return ColorResponseDto.builder()
                .id(color.getId())
                .name(color.getName())
                .hexCode(color.getHexCode())
                .build();
    }
    public static Size toEntity(SizeRequestDto dto){
        return Size.builder()
                .name(dto.getName())
                .build();
    }
    public static SizeResponseDto toResponse(Size size){
        return SizeResponseDto.builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }
}

