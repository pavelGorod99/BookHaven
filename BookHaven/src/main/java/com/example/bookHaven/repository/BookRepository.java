package com.example.bookHaven.repository;

//import com.example.bookHaven.dto.BookAuthorDto;
import com.example.bookHaven.dto.BookDto;
import com.example.bookHaven.dto.BookWithAuthorsDto;
import com.example.bookHaven.dto.InventoryLevelByGenreDto;
import com.example.bookHaven.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Add custom query methods if needed
//    @Query(value =  "SELECT NEW com.example.bookHaven.dto.BookWithAuthorsDto(b.bookId, b.title, b.genre, b.price, b.quantity, (SELECT NEW com.example.bookHaven.dto.BookAuthorDto())) FROM Book b " + "INNER JOIN b.authors a WHERE b.title LIKE %:title%")
//    @Query(value =  "SELECT b.book_id AS bookId, b.title, b.genre, b.price, b.quantity, GROUP_CONCAT(a.first_name) AS firstName, GROUP_CONCAT(a.last_name) AS lastName FROM book b " +
//                    "INNER JOIN author_books ab ON b.book_id = ab.book_id " +
//                    "INNER JOIN author a ON ab.author_id = a.id " +
//                    "WHERE b.title LIKE %:title%", nativeQuery = true)
    @Query(value = "SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> findAllBooksByTitle(@Param("title") String title);

    @Query(value = "SELECT b FROM Book b WHERE b.inTrend IS TRUE")
    List<Book> findAllByInTrend();

    @Query(value = "SELECT bt.quantity FROM Book b " +
                   "INNER JOIN BookTransaction bt ON b.bookId = bt.id.book.bookId " +
                   "INNER JOIN Transaction t ON bt.id.transaction.transactionId = t.transactionId " +
                   "WHERE b.quantity > 0 AND t.transactionDateTime BETWEEN :startDate and :endDate GROUP BY bt.quantity ORDER BY bt.quantity DESC")
    List<Integer> findBestSoldQuantity(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    @Query(value = "SELECT b FROM Book b " +
                   "INNER JOIN BookTransaction bt ON b.bookId = bt.id.book.bookId " +
                   "INNER JOIN Transaction t ON bt.id.transaction.transactionId = t.transactionId " +
                   "WHERE b.quantity > 0 AND (bt.quantity = :max1 OR bt.quantity = :max2) AND t.transactionDateTime BETWEEN :startDate and :endDate ORDER BY bt.quantity DESC")
    List<Book> findAllBestSellingBooks(@Param("max1") Integer max1, @Param("max2") Integer max2, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    @Query(value = "SELECT NEW com.example.bookHaven.dto.InventoryLevelByGenreDto(COUNT(b.bookId), b.genre) FROM Book b GROUP BY b.genre ORDER BY COUNT(b.bookId) DESC")
    List<InventoryLevelByGenreDto> findAllInInventoryLevelByGenre();
}

