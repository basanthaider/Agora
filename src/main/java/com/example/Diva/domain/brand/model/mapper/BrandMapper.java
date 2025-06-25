package com.example.Diva.domain.brand.model.mapper;

import com.example.Diva.domain.brand.model.request.BrandRequestDto;
import com.example.Diva.domain.brand.model.response.BrandResponseDto;
import com.example.Diva.entity.Brand;
import lombok.Builder;

@Builder
public class BrandMapper {
    public static Brand toEntity(BrandRequestDto dto) {
        return Brand.builder()
                .name(dto.getName())
                .build();
    }

    public static BrandResponseDto toResponse(Brand brand) {
        return BrandResponseDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
