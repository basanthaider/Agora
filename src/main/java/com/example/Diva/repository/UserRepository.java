package com.example.Diva.repository;

import com.example.Diva.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Additional query methods can be defined here if needed

}
