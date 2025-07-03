package com.example.Diva.domain.cart.service.implementation;

import com.example.Diva.domain.authentication.service.contractor.AuthenticationService;
import com.example.Diva.domain.cart.model.mapper.CartMapper;
import com.example.Diva.domain.cart.model.request.CartItemRequestDto;
import com.example.Diva.domain.cart.model.response.CartResponseDto;
import com.example.Diva.domain.cart.service.contractor.CartService;
import com.example.Diva.entity.Cart;
import com.example.Diva.entity.CartItem;
import com.example.Diva.entity.User;
import com.example.Diva.repository.CartItemRepository;
import com.example.Diva.repository.CartRepository;
import com.example.Diva.repository.ProductVariantRepository;
import com.example.Diva.repository.UserRepository;
import com.example.Diva.utill.BaseResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductVariantRepository variantRepository;
    @Autowired
    private CartItemRepository itemRepository;
    @Autowired
    AuthenticationService authService;

    private User getCurrentCustomer() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedInUserEmail;
        if (principal instanceof UserDetails) {
            loggedInUserEmail = ((UserDetails) principal).getUsername();
        } else {
            loggedInUserEmail = principal.toString();
        }
        User loggedInUser;
        loggedInUser = userRepository.findByEmail(loggedInUserEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + loggedInUserEmail));
        return loggedInUser;
    }

    @Transactional
    @Override
    public ResponseEntity<Object> addItemToCart(CartItemRequestDto cartItemRequestDto) {
        User currentUser = getCurrentCustomer();
        if (currentUser == null) {
            return new ResponseEntity<>(new BaseResponse<>(false, "you need to register/login", null), HttpStatus.UNAUTHORIZED);
        }
        var variant = variantRepository.findById(cartItemRequestDto.getVariantId())
                .orElseThrow(() -> new RuntimeException("Product variant not found"));

        Cart cart = getOrCreateCart();

        Optional<CartItem> existing = cart.getItems().stream()
                .filter(i -> i.getVariant().getId().equals(cartItemRequestDto.getVariantId()))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + cartItemRequestDto.getQuantity());
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setVariant(variant);
            item.setQuantity(cartItemRequestDto.getQuantity());
            cart.getItems().add(item);
        }

        cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartMapper.toResponse(cart);

        return new ResponseEntity<>(new BaseResponse<>(true, "Item added to cart successfully", cartResponseDto), HttpStatus.CREATED);
    }


    private Cart getOrCreateCart() {
        User user = getCurrentCustomer();
        Optional<Cart> optionalCart = cartRepository.findByUser(user);
        if (optionalCart.isPresent()) {
            return optionalCart.get();
        } else {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        }
    }

    @Override
    public ResponseEntity<Object> removeItemFromCart(Long itemId) {
        User currentUser = getCurrentCustomer();
        if (currentUser == null) {
            return new ResponseEntity<>(new BaseResponse<>(false, "you need to register/login", null), HttpStatus.UNAUTHORIZED);
        }

        Cart cart = getOrCreateCart();
        CartItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));

        if (!cart.getItems().contains(item)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Item not found in cart", null), HttpStatus.NOT_FOUND);
        }

        cart.getItems().remove(item);
        itemRepository.delete(item);
        cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartMapper.toResponse(cart);

        return new ResponseEntity<>(new BaseResponse<>(true, "Item removed from cart successfully", cartResponseDto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Object> updateCartItemQuantity(Long itemId, Integer quantity) {
        User currentUser = getCurrentCustomer();
        if (currentUser == null) {
            return new ResponseEntity<>(new BaseResponse<>(false, "you need to register/login", null), HttpStatus.UNAUTHORIZED);
        }

        Cart cart = getOrCreateCart();
        CartItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));

        if (!cart.getItems().contains(item)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Item not found in cart", null), HttpStatus.NOT_FOUND);
        }

        item.setQuantity(quantity);
        itemRepository.save(item);

        CartResponseDto cartResponseDto = CartMapper.toResponse(cart);

        return new ResponseEntity<>(new BaseResponse<>(true, "Item quantity updated successfully", cartResponseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getCartDetails() {
        User currentUser = getCurrentCustomer();
        if (currentUser == null) {
            return new ResponseEntity<>(new BaseResponse<>(false, "you need to register/login", null), HttpStatus.UNAUTHORIZED);
        }

        Cart cart = getOrCreateCart();
        CartResponseDto cartResponseDto = CartMapper.toResponse(cart);

        return new ResponseEntity<>(new BaseResponse<>(true, "Cart details retrieved successfully", cartResponseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> clearCart() {
        User currentUser = getCurrentCustomer();
        if (currentUser == null) {
            return new ResponseEntity<>(new BaseResponse<>(false, "you need to register/login", null), HttpStatus.UNAUTHORIZED);
        }

        Cart cart = getOrCreateCart();
        cart.getItems().clear();
        cartRepository.save(cart);

        return new ResponseEntity<>(new BaseResponse<>(true, "Cart cleared successfully", null), HttpStatus.OK);
    }
}
