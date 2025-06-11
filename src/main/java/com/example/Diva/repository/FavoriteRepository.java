package com.example.Diva.repository;

import com.example.Diva.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // Additional query methods can be defined here if needed
}
