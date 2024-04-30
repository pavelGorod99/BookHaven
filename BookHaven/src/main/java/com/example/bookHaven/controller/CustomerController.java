package com.example.bookHaven.controller;

import com.example.bookHaven.dto.CustomerDto;
import com.example.bookHaven.dto.CustomerUserDto;
import com.example.bookHaven.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // TO DO LOG-IN PART FOR CUSTOMERS

    // END-POINT FOR STUFF AND ADMIN
    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // END-POINT FOR STUFF AND ADMIN
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        CustomerDto customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    // END-POINT FOR STUFF AND ADMIN AND CUSTOMER
    @PostMapping("/")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerUserDto customerDto) {
        CustomerDto savedCustomer = customerService.saveCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    // END-POINT FOR STUFF AND ADMIN AND CUSTOMERS
    @PutMapping("/")
    public ResponseEntity<Void> updateCustomer(@RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerDto);
        return ResponseEntity.ok().build();
    }

    // END-POINT FOR STUFF AND ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
