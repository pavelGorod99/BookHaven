package com.example.bookHaven.repository;

import com.example.bookHaven.dto.CustomReportDto;
import com.example.bookHaven.model.Book;
import com.example.bookHaven.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportingRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT NEW com.example.bookHaven.dto.CustomReportDto(b.bookId, b.title, b.genre, b.inTrend, b.price, b.quantity, bt.quantity, t.transactionId, t.discount, t.totalAmount, t.transactionDateTime, CONCAT(c.firstName, \" \", c.lastName)) FROM Book b " +
                   "INNER JOIN BookTransaction bt ON b.bookId = bt.id.book.bookId " +
                   "INNER JOIN Transaction t ON bt.id.transaction.transactionId = t.transactionId " +
                   "INNER JOIN Customer c ON t.customer.customerId = c.customerId " +
                   "WHERE t.transactionDateTime BETWEEN :startDate and :endDate ORDER BY t.transactionDateTime")
    List<CustomReportDto> getCustomReports(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT NEW com.example.bookHaven.dto.CustomReportDto(b.bookId, b.title, b.genre, b.inTrend, b.price, b.quantity, bt.quantity, t.transactionId, t.discount, t.totalAmount, t.transactionDateTime, CONCAT(c.firstName, \" \", c.lastName)) FROM Book b " +
            "INNER JOIN BookTransaction bt ON b.bookId = bt.id.book.bookId " +
            "INNER JOIN Transaction t ON bt.id.transaction.transactionId = t.transactionId " +
            "INNER JOIN Customer c ON t.customer.customerId = c.customerId " +
            "WHERE b.genre=:genre ORDER BY t.transactionDateTime")
    List<CustomReportDto> getCustomReportsByGenre(@Param("genre") Genre genre);
}
