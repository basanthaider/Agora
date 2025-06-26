package com.example.Diva.domain.product.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ColorResponseDto {
    private Long id;
    private String name;
    private String hexCode;
}
