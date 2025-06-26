package com.example.Diva.domain.product.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SizeRequestDto {
    @NotBlank(message = "Size must not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\.\\-\\/]+$",
            message = "Size can contain letters, numbers, spaces, periods, hyphens, or slashes (e.g., 'S', '42', '7.5', 'S-M', 'One Size')")
    private String name;
}
