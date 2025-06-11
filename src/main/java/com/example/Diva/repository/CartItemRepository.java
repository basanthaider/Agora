package com.example.Diva.repository;

import com.example.Diva.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Additional query methods can be defined here if needed
}
