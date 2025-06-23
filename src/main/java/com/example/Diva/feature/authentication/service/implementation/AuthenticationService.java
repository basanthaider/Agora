package com.example.Diva.feature.authentication.service.implementation;

import com.example.Diva.entity.User;
import com.example.Diva.feature.authentication.model.request.LogoutRequestDto;
import com.example.Diva.feature.authentication.service.contractor.Authentication;
import com.example.Diva.repository.UserRepository;
import com.example.Diva.security.entity.Token;
import com.example.Diva.feature.authentication.model.request.LoginRequestDto;
import com.example.Diva.feature.authentication.model.request.RegisterRequestDto;
import com.example.Diva.feature.authentication.model.response.AuthenticationResponseDto;
import com.example.Diva.security.repository.TokenRepository;
import com.example.Diva.security.service.JwtService;
import com.example.Diva.utill.BaseEnums;
import com.example.Diva.utill.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService implements Authentication {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Object> register(RegisterRequestDto request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ResponseEntity<>(new BaseResponse<>(true, "Password doesn't match", null), HttpStatus.BAD_REQUEST);
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
        AuthenticationResponseDto responseDto = new AuthenticationResponseDto(jwt);

        return new ResponseEntity<>(new BaseResponse<>(true, "You have just registered successfully", responseDto), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> login(LoginRequestDto request) {
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
            return new ResponseEntity<>(new BaseResponse<>(false,  "Invalid email or password", null), HttpStatus.UNAUTHORIZED);

        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found after successful authentication (this should not happen)")); // Added more descriptive message

        String jwt = jwtService.generateToken(user.getEmail());
        saveUserToken(jwt, user);
        log.info("Login successful, JWT generated for email: {}", request.getEmail());
        AuthenticationResponseDto responseDto = new AuthenticationResponseDto(jwt);

        return new ResponseEntity<>(new BaseResponse<>(true, "Logged in successfully", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> logout(LogoutRequestDto logoutRequestDto) {
        String token = logoutRequestDto.getToken();
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body(new BaseResponse<>(false,"Token is required for logout",null));
        }

        Token existingToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        existingToken.setLoggedOut(true);
        tokenRepository.save(existingToken);

        return ResponseEntity.ok().body(new BaseResponse<>(true,"Logged out successfully",null));
    }

    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

}
