package com.example.bookHaven.repository;

import com.example.bookHaven.dto.CustomerPurchaseHistoryDto;
import com.example.bookHaven.dto.RevenuePerMonthDto;
import com.example.bookHaven.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Add custom query methods if needed
    @Query("SELECT NEW com.example.bookHaven.dto.CustomerPurchaseHistoryDto(t.transactionId, b.title, bt.quantity, t.totalAmount, t.transactionDateTime) " +
           "FROM Transaction t " +
           "INNER JOIN BookTransaction bt ON t.transactionId = bt.id.transaction.transactionId " +
           "INNER JOIN Book b ON bt.id.book.bookId = b.bookId " +
           "WHERE t.customer.customerId = :customerId")
    List<CustomerPurchaseHistoryDto> getTransactionByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "SELECT NEW com.example.bookHaven.dto.RevenuePerMonthDto(SUM(t.totalAmount), SUM(bt.quantity)) " +
                   "FROM Transaction t " +
                   "INNER JOIN BookTransaction bt ON t.transactionId = bt.id.transaction.transactionId " +
                   "WHERE t.transactionDateTime BETWEEN :startDate AND :endDate")
    RevenuePerMonthDto revenueSummaries(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
