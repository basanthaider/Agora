package com.example.Diva.domain.product.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SizeResponseDto {
    private Long id;
    private String name;
}
