package com.example.eLibrary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(unique = true)
    private String title;
    private String genre;
    private BigDecimal price;
    private int quantity;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;
}