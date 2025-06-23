package com.example.Diva.feature.authentication.service.contractor;

import com.example.Diva.feature.authentication.model.request.LoginRequestDto;
import com.example.Diva.feature.authentication.model.request.LogoutRequestDto;
import com.example.Diva.feature.authentication.model.request.RegisterRequestDto;
import org.springframework.http.ResponseEntity;

public interface Authentication {
    ResponseEntity<Object> register(RegisterRequestDto registerRequestDto);
    ResponseEntity<Object> login(LoginRequestDto loginRequestDto);
    ResponseEntity<Object> logout(LogoutRequestDto logoutRequestDto);


}
