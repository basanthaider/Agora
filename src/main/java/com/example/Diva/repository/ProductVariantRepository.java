package com.example.Diva.repository;

import com.example.Diva.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    // Additional query methods can be defined here if needed
}
