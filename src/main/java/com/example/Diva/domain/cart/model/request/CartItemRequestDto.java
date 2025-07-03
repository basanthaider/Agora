package com.example.Diva.domain.cart.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemRequestDto {
    private Long variantId;
    @NotBlank(message = "Product Quantity cannot be blank")
    private Integer quantity;
}
