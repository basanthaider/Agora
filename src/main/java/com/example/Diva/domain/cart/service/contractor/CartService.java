package com.example.Diva.domain.cart.service.contractor;

import com.example.Diva.domain.cart.model.request.CartItemRequestDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<Object> addItemToCart(CartItemRequestDto cartItemRequestDto);
    ResponseEntity<Object> removeItemFromCart(Long itemId);
    ResponseEntity<Object> updateCartItemQuantity(Long itemId, Integer quantity);
    ResponseEntity<Object> getCartDetails();
    ResponseEntity<Object> clearCart();

}
