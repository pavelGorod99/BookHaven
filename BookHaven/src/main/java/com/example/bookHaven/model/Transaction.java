package com.example.bookHaven.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private double totalAmount;

    private int discount;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime transactionDateTime;
}

