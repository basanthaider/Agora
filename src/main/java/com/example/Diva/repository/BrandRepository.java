package com.example.Diva.repository;

import com.example.Diva.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    // Additional query methods can be defined here if needed
}
