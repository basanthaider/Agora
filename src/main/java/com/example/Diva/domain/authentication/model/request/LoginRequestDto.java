package com.example.Diva.domain.authentication.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @Email(message = "Email should be valid")@NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;

}

