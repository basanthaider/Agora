package com.example.Diva.domain.product.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColorRequestDto {
    @NotBlank(message = "Color name must not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Color name can only contain letters, numbers, and spaces.")
    @Size(max = 50, message = "Color name must not exceed 50 characters.")
    private String name;

    @NotBlank(message = "Hex code must not be empty.")
    @Pattern(regexp = "^#([0-9a-fA-F]{3}|[0-9a-fA-F]{6}|[0-9a-fA-F]{8})$",
            message = "Hex code must be a valid 3, 6, or 8 digit hexadecimal color code (e.g., #FFF, #RRGGBB, #RRGGBBAA).")
    private String hexCode;
}
