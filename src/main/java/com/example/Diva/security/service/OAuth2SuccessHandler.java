package com.example.Diva.security.service;

import com.example.Diva.entity.User;
import com.example.Diva.repository.UserRepository;
import com.example.Diva.security.entity.Token;
import com.example.Diva.security.repository.TokenRepository;
import com.example.Diva.utill.BaseEnums;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");


        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setProvider("GOOGLE");
            newUser.setRole(BaseEnums.Role.CUSTOMER);
            newUser.setPassword(""); // Not needed for Google
            return userRepository.save(newUser);
        });

        String jwt = jwtService.generateToken(user);
        tokenRepository.save(new Token(jwt, OAuth2AccessToken.TokenType.BEARER, false, user));

        // Redirect to frontend with JWT token
        String redirectUrl = "http://localhost:8081/oauth2-success?token=" + jwt; // <- change as needed
        response.sendRedirect(redirectUrl);
    }
}


