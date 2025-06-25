package com.example.Diva.domain.brand.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class BrandRequestDto {
    @NotBlank(message = "Brand name cannot be blank")
    String name;

}
