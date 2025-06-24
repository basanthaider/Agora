package com.example.Diva.domain.authentication.model.response;

import com.example.Diva.utill.BaseEnums;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String token;
    private BaseEnums.Role role;
    private String name;
    private String email;
}
