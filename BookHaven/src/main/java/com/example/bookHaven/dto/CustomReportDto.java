package com.example.bookHaven.dto;

import com.example.bookHaven.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomReportDto {

    private Long bookId;
    private String bookTitle;
    private Genre bookGenre;
    private boolean inTrend;
    private double bookPrice;
    private int quantityLeft;
    private int quantitySold;
    private Long transactionId;
    private int discount;
    private double totalAmount;
    private LocalDateTime transactionDateTime;
    private String customerFullName;
}
