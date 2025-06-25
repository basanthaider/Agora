package com.example.Diva.domain.brand.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class BrandResponseDto {
    Long id;
    String name;
}
