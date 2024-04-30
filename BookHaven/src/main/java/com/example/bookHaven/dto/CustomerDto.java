package com.example.bookHaven.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Long customerId;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
}