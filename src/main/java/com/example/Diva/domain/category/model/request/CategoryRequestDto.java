package com.example.Diva.domain.category.model.request;

import com.example.Diva.utill.BaseEnums;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Type cannot be blank")
    private BaseEnums.CategoryType type;
}
