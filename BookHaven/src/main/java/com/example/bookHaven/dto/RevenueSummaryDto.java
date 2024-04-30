package com.example.bookHaven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueSummaryDto {

    private String month;
    private Long quantity;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalAmount;
}
