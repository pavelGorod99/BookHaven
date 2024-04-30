package com.example.bookHaven.repository;

import com.example.bookHaven.model.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction, Long> {
//    void deleteById(Long bookId, Long transactionId);
    // Add custom query methods if needed
}