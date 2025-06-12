package com.example.Diva.repository;

import com.example.Diva.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional query methods can be defined here if needed
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);



}
