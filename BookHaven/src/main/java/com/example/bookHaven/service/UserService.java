package com.example.bookHaven.service;

import com.example.bookHaven.dto.CustomerUserDto;
import com.example.bookHaven.model.User;

public interface UserService {
    void createCustomer(CustomerUserDto customerUserDto);
    void createStuffOrAdmin(User user);
    User getUserByUsername(String username);
    User createUser(User user);
}
