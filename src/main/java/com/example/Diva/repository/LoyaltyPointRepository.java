package com.example.Diva.repository;

import com.example.Diva.entity.LoyaltyPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyPointRepository extends JpaRepository<LoyaltyPoint, Long> {

    // Additional query methods can be defined here if needed
}
