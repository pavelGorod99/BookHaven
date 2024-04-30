package com.example.bookHaven.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "book_transaction")
public class BookTransaction {

    @EmbeddedId
    private BookTransactionId id;

    private int quantity;
}

