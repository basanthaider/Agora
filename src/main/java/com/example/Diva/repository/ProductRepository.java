package com.example.Diva.repository;

import com.example.Diva.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Additional query methods can be defined here if needed
}
