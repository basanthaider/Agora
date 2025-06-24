package com.example.Diva.domain.authentication.service.contractor;

import com.example.Diva.domain.authentication.model.request.LoginRequestDto;
import com.example.Diva.domain.authentication.model.request.LogoutRequestDto;
import com.example.Diva.domain.authentication.model.request.RegisterRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<Object> register(RegisterRequestDto registerRequestDto);
    ResponseEntity<Object> login(LoginRequestDto loginRequestDto);
    ResponseEntity<Object> logout(LogoutRequestDto logoutRequestDto);


}
