package com.example.bookHaven.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    private Long bookId;
    private String title;
    private String author;
    private String genre;
    private BigDecimal price;
    private int quantity;
}
