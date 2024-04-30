package com.example.bookHaven.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(unique = true)
    private String title;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private double price;
    private int quantity;
    private boolean inTrend;

    public Book(String title, Genre genre, double price, int quantity, boolean inTrend, Set<Author> authors) {
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
        this.inTrend = inTrend;
        this.authors = authors;
    }

    @ManyToMany
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonManagedReference
    private Set<Author> authors;
}