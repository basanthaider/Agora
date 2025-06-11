package com.example.Diva.repository;

import com.example.Diva.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Long> {

    // Additional query methods can be defined here if needed
}
