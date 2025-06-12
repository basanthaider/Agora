package com.example.Diva.security.service;

import com.example.Diva.entity.User;
import com.example.Diva.repository.UserRepository;
import com.example.Diva.security.entity.Token;
import com.example.Diva.security.model.request.LoginRequestDto;
import com.example.Diva.security.model.request.RegisterRequestDto;
import com.example.Diva.security.model.response.AuthenticationResponseDto;
import com.example.Diva.security.repository.TokenRepository;
import com.example.Diva.utill.BaseEnums;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  TokenRepository tokenRepository;
    private final  PasswordEncoder passwordEncoder;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .enabled(true)
                .password(passwordEncoder.encode(request.getPassword()))
                .provider("LOCAL")
                .role(BaseEnums.Role.CUSTOMER)
                .build();

        userRepository.save(user);

        String jwt = jwtService.generateToken(user.getEmail());
        saveUserToken(jwt, user);

        return new AuthenticationResponseDto(jwt);
    }

    public AuthenticationResponseDto login(LoginRequestDto request) {
        log.info("Attempting login for email: {}", request.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            log.info("Authentication successful for email: {}", request.getEmail());

        } catch (AuthenticationException e) {
            log.error("Authentication failed for email: {}. Reason: {}", request.getEmail(), e.getMessage());
            // Re-throw the exception or throw a custom one if needed for controller advice
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found after successful authentication (this should not happen)")); // Added more descriptive message

        String jwt = jwtService.generateToken(user.getEmail());
        saveUserToken(jwt, user);
        log.info("Login successful, JWT generated for email: {}", request.getEmail());

        return new AuthenticationResponseDto(jwt);
    }

    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }
    public void logout(String token) {
        Token existingToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        existingToken.setLoggedOut(true);
        tokenRepository.save(existingToken);
    }
}
