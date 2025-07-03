package com.example.Diva.domain.cart.controller;

import com.example.Diva.domain.cart.model.request.CartItemRequestDto;
import com.example.Diva.domain.cart.service.contractor.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/cart")
@Validated
@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping
    public ResponseEntity<Object> addItemToCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
        return ResponseEntity.ok(cartService.addItemToCart(cartItemRequestDto)) ;
    }

    @GetMapping
    public ResponseEntity<Object> getCart() {
        return ResponseEntity.ok(cartService.getCartDetails()) ;
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Object> updateCartItemQuantity(@PathVariable Long itemId, Integer quantity) {
        return ResponseEntity.ok(cartService.updateCartItemQuantity(itemId, quantity)) ;
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Object> removeItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(itemId)) ;
    }
    @DeleteMapping
    public ResponseEntity<Object> clearCart() {
        return ResponseEntity.ok(cartService.clearCart()) ;
    }

}
