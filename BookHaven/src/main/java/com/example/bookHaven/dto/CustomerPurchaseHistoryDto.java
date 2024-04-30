package com.example.bookHaven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomerPurchaseHistoryDto {
    private Long transactionId;
    private String bookTitle;
    private int quantity;
    private double totalAmount;
    private LocalDateTime transactionDateTime;
}
