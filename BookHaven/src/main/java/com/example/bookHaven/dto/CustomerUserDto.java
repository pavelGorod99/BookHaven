package com.example.bookHaven.dto;

import lombok.Data;

@Data
public class CustomerUserDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String address;
}
