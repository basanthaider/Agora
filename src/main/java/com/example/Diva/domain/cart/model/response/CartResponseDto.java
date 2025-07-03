package com.example.Diva.domain.cart.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponseDto {
    private Long id;
    private Double totalPrice;
    private Integer itemCount;
    private List<CartItemResponseDto> items;
}
