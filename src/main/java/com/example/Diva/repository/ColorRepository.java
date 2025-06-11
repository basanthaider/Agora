package com.example.Diva.repository;

import com.example.Diva.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {

    // Additional query methods can be defined here if needed
}
