package com.example.bookHaven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RevenuePerMonthDto {
    private Double totalAmount;
    private Long quantity;
}
