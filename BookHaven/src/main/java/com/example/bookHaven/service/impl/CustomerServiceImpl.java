package com.example.bookHaven.service.impl;

import com.example.bookHaven.dto.CustomerDto;
import com.example.bookHaven.dto.CustomerUserDto;
import com.example.bookHaven.model.Customer;
import com.example.bookHaven.model.Role;
import com.example.bookHaven.model.User;
import com.example.bookHaven.repository.CustomerRepository;
import com.example.bookHaven.repository.UserRepository;
import com.example.bookHaven.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(BCryptPasswordEncoder passwordEncoder, CustomerRepository customerRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerList.forEach(customer -> {
            CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
            customerDtoList.add(customerDto);
        });
        return customerDtoList;
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }

    @Override
    public CustomerDto saveCustomer(CustomerUserDto customerDto) {
//        Customer customer = modelMapper.map(customerDto, Customer.class);
        User user = userRepository.save(new User(customerDto.getUsername(), passwordEncoder.encode(customerDto.getPassword()), Role.CUSTOMER, true, true, true, true));
        Customer customer = new Customer();

        customer.setUser(user);
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());

        customer = customerRepository.save(customer);
        customerDto.setCustomerId(customer.getCustomerId());

        CustomerDto cd = new CustomerDto();
        cd.setCustomerId(customer.getCustomerId());
        cd.setFirstName(customer.getFirstName());
        cd.setLastName(customer.getLastName());
        cd.setPhone(customer.getPhone());
        cd.setEmail(customer.getEmail());
        cd.setAddress(customer.getAddress());

        return cd;
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerDto.getCustomerId());
        System.out.println("CUSTOMER PRESENT: " + optionalCustomer.isPresent());
        if (optionalCustomer.isPresent()) {
            Customer customer = modelMapper.map(customerDto, Customer.class);
            customerRepository.save(customer);
        } else {
            throw new NoSuchElementException("Customer not found");
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

