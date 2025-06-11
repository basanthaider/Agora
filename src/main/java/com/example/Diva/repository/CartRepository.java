package com.example.Diva.repository;

import com.example.Diva.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // Additional query methods can be defined here if needed
}
