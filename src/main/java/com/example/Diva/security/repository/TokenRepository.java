package com.example.Diva.security.repository;

import com.example.Diva.entity.User;
import com.example.Diva.security.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllByUserAndIsLoggedOutFalse(User user);
    Optional<Token> findByToken(String token);
}
