package com.example.bookHaven.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class TransactionItemDto {
    private Long customerId;
    private Long bookId;
    private int quantity;
    private int discount;
}
