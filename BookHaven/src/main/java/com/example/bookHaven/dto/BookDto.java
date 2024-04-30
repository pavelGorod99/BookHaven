package com.example.bookHaven.dto;

import com.example.bookHaven.model.Genre;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Setter
@Getter
public class BookDto {
    private Long bookId;
    private String title;
    private Genre genre;
    private double price;
    private int quantity;
    private boolean inTrend;
    private Set<Long> authors;
}
