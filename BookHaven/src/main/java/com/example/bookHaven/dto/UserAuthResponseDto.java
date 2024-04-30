package com.example.bookHaven.dto;

import com.example.bookHaven.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthResponseDto {
    private String jwtToken;
    private Role role;
//    private String firstName;
//    private String lastName;
}
