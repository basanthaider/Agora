package com.example.Diva.domain.cart.model.response;

import com.example.Diva.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemResponseDto {
    private Long id;
    private Long variantId;
    private String productName;
    private List<String> variantImageUrl;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
}
