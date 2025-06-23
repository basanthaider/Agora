package com.example.Diva.feature.authentication.model.request;

import com.example.Diva.utill.BaseEnums;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Email should be valid")@NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;
    private BaseEnums.Role role = BaseEnums.Role.CUSTOMER;
}
