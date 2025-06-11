package com.example.Diva.repository;

import com.example.Diva.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    // Additional query methods can be defined here if needed
}
