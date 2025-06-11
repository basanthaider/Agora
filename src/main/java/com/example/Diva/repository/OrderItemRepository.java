package com.example.Diva.repository;

import com.example.Diva.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Additional query methods can be defined here if needed
}
