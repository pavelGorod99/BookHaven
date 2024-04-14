package com.example.eLibrary.dto;

import com.example.eLibrary.model.Book;
import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {
    private String firstName;
    private String lastName;
    private List<Book> books;
}
