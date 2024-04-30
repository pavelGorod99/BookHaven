package com.example.bookHaven.dto;

import com.example.bookHaven.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryLevelByGenreDto {
    private Long amount;
    private Genre genre;
}
