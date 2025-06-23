package com.example.Diva.domain.authentication.model.request;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;

}

