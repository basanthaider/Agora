package com.example.Diva.repository;

import com.example.Diva.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByVariantId(Long variantId);

}
