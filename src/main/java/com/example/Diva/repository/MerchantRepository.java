package com.example.Diva.repository;

import com.example.Diva.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    // Additional query methods can be defined here if needed
}
