package com.example.Diva.domain.cart.model.mapper;

import com.example.Diva.domain.cart.model.request.CartItemRequestDto;
import com.example.Diva.domain.cart.model.response.CartItemResponseDto;
import com.example.Diva.domain.cart.model.response.CartResponseDto;
import com.example.Diva.entity.Cart;
import com.example.Diva.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
public class CartMapper {
    public static CartResponseDto toResponse(Cart cart) {
        List<CartItemResponseDto> items = cart.getItems().stream()
                .map(item -> CartItemResponseDto.builder()
                        .id(item.getId())
                        .variantId(item.getVariant().getId())
                        .productName(item.getVariant().getProduct().getName())
                        .variantImageUrl(item.getVariant().getImages().stream()
                                .map(ProductImage::getImageUrl)
                                .toList())
                        .quantity(item.getQuantity())
                        .price(item.getVariant().getPrice())
                        .totalPrice(item.getTotalPrice())
                        .build())
                .toList();
        return CartResponseDto.builder()
                .id(cart.getId())
                .totalPrice(cart.getTotalPrice())
                .itemCount(cart.getItems().size())
                .items(items)
                .build();
    }
}
