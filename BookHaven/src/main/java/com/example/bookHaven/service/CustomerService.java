package com.example.bookHaven.service;

import com.example.bookHaven.dto.CustomerDto;
import com.example.bookHaven.dto.CustomerUserDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Long id);
    CustomerDto saveCustomer(CustomerUserDto customerDto);
    void updateCustomer(CustomerDto customerDto);
    void deleteCustomer(Long id);
}
