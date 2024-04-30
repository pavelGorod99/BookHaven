package com.example.bookHaven.dto;

import com.example.bookHaven.model.Book;
import lombok.Data;

import java.util.List;

@Data
public class AuthorWithBooksDto {
    private String firstName;
    private String lastName;
    private List<Book> books;
}
