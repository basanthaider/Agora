package com.example.Diva.repository;

import com.example.Diva.entity.Cart;
import com.example.Diva.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User currentUser);
}
