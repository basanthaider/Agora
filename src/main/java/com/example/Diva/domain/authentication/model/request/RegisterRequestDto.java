package com.example.Diva.domain.authentication.model.request;

import com.example.Diva.utill.BaseEnums;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Email should be valid")@NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-_+=])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;
    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;
    private BaseEnums.Role role = BaseEnums.Role.CUSTOMER;
}
