package com.example.Diva.domain.category.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class SubCategoryRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Category ID cannot be blank")
    private Long categoryId;

}
