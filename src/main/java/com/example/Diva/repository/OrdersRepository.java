package com.example.Diva.repository;

import com.example.Diva.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    // Additional query methods can be defined here if needed
}
