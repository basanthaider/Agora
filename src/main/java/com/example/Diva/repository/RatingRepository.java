package com.example.Diva.repository;

import com.example.Diva.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    // Additional query methods can be defined here if needed
}
