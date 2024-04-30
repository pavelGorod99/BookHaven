package com.example.bookHaven.service.impl;

import com.example.bookHaven.dto.CustomerUserDto;
import com.example.bookHaven.model.*;
import com.example.bookHaven.repository.AuthorRepository;
import com.example.bookHaven.repository.BookRepository;
import com.example.bookHaven.repository.CustomerRepository;
import com.example.bookHaven.repository.UserRepository;
import com.example.bookHaven.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService, ApplicationRunner {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public UserServiceImpl(CustomerRepository customerRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createCustomer(CustomerUserDto customerUserDto) {

        User user = new User();
        user.setUsername(customerUserDto.getUsername());
        user.setPassword(customerUserDto.getPassword());
        user.setRole(Role.CUSTOMER);

        user = userRepository.save(user);

        Customer customer = new Customer();

        customer.setFirstName(customerUserDto.getFirstName());
        customer.setLastName(customerUserDto.getLastName());
        customer.setEmail(customerUserDto.getEmail());

        customer.setPhone(customerUserDto.getPhone());
        customer.setAddress(customerUserDto.getAddress());

        customer.setUser(user);

        customerRepository.save(customer);
    }

    @Override
    public void createStuffOrAdmin(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    private void addDummyData(){

        authorRepository.saveAll(
                Arrays.asList(
                        new Author("John", "Smith"),
                        new Author("Sarah", "Johnson"),
                        new Author("Michael", "Brown"),
                        new Author("Emily", "Davis"),
                        new Author("David", "Wilson")
                )
        );

//        bookRepository.saveAll(
//                Arrays.asList(
//                        new Book("In the heaven 1", Genre.HISTORICAL_FICTION, 250, 5, true, new HashSet<>().add())
//                )
//        );

        userRepository.saveAll(
                Arrays.asList(
                        new User("paveladmin", passwordEncoder.encode("12345"), Role.ADMIN, true, true, true, true),
                        new User("pavelstuff", passwordEncoder.encode("12345"), Role.STUFF, true, true, true, true),
                        new User("pavelcustomer1", passwordEncoder.encode("12345"), Role.CUSTOMER, true, true, true, true),
                        new User("pavelcustomer2", passwordEncoder.encode("12345"), Role.CUSTOMER, true, true, true, true),
                        new User("pavelcustomer3", passwordEncoder.encode("12345"), Role.CUSTOMER, true, true, true, true)
                )
        );
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addDummyData();
    }
}
