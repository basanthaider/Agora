package com.example.Diva.security.entity;

import com.example.Diva.entity.BaseEntity;
import com.example.Diva.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.oauth2.core.OAuth2AccessToken;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Token extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    public boolean isLoggedOut = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Token(String jwt, OAuth2AccessToken.TokenType bearer, boolean isLoggedOut, User user) {
        super();
    }

    public boolean isLoggedOut() {
        return false;
    }
}
