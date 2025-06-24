package com.example.Diva.domain.authentication.controller;

import com.example.Diva.domain.authentication.model.request.LoginRequestDto;
import com.example.Diva.domain.authentication.model.request.LogoutRequestDto;
import com.example.Diva.domain.authentication.model.request.RegisterRequestDto;
import com.example.Diva.domain.authentication.service.implementation.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
@SecurityRequirement(name = "bearerAuth")
public class AuthenticationController {
    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        LogoutRequestDto logoutRequestDto = new LogoutRequestDto();
        logoutRequestDto.setToken(token);
       return ResponseEntity.ok( authenticationService.logout(logoutRequestDto));
    }
}