package com.example.bookHaven.dto;

import com.example.bookHaven.model.Customer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private Long transactionId;
    private Customer customer;
    private double totalAmount;
    private LocalDateTime transactionDateTime;
}
