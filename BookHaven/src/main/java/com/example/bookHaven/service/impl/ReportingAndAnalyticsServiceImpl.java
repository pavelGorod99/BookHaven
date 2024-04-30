package com.example.bookHaven.service.impl;

import com.example.bookHaven.dto.*;
import com.example.bookHaven.model.Book;
import com.example.bookHaven.model.Genre;
import com.example.bookHaven.repository.BookRepository;
import com.example.bookHaven.repository.ReportingRepository;
import com.example.bookHaven.repository.TransactionRepository;
import com.example.bookHaven.service.ReportingAndAnalyticsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReportingAndAnalyticsServiceImpl implements ReportingAndAnalyticsService {

    private final BookRepository bookRepository;
    private final TransactionRepository transactionRepository;
    private final ReportingRepository reportingRepository;

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ReportingAndAnalyticsServiceImpl(BookRepository bookRepository, TransactionRepository transactionRepository, ReportingRepository reportingRepository) {
        this.bookRepository = bookRepository;
        this.transactionRepository = transactionRepository;
        this.reportingRepository = reportingRepository;
    }

    @Override
    public void reportingAndAnalytics() {
        // TO DO => SALES TRENDS
//        List<Book> salesTrends = bookRepository.findAllByInTrend();
//        System.out.println(salesTrends);


        // BEST-SELLING BOOKS
//        Pageable pageable = PageRequest.of(0, 2);
//        LocalDateTime current = LocalDateTime.now();
//        LocalDateTime lastDate = LocalDateTime.now().minusMonths(1);
//
//        List<Integer> bestSellingQuantity = bookRepository.findBestSoldQuantity(lastDate, current, pageable);
//        System.out.println("BEST SOLD QUANTITY");
//        System.out.println(bestSellingQuantity);
//
//        int max1 = 0;
//        int max2 = 0;
//
//        if (bestSellingQuantity.size() > 0) {
//            max1 = bestSellingQuantity.get(0);
//        }
//
//        if (bestSellingQuantity.size() > 1) {
//            max2 = bestSellingQuantity.get(1);
//        }
//
//        List<Book> bestSellingBooks = bookRepository.findAllBestSellingBooks(max1, max2, lastDate, current, pageable);
//        System.out.println(bestSellingBooks);


        // INVENTORY LEVELS
//        List<InventoryLevelByGenreDto> inventoryList = bookRepository.findAllInInventoryLevelByGenre();
//        System.out.println(inventoryList);


        // REVENUE SUMMARIES
//        List<RevenueSummaryDto> revenueSummariesList = new ArrayList<>();
//        for (int i = 12; i >= 1; i--) {
//            System.out.println("START DATE: " + lastDate + " END DATE: " + current);
//            RevenuePerMonthDto revenuePerMonth = transactionRepository.revenueSummaries(lastDate, current);
//            if (revenuePerMonth.getQuantity() != null && revenuePerMonth.getTotalAmount() != null) {
//
//                System.out.println("REVENUE SUMMARIES: " + revenuePerMonth);
//
//                RevenueSummaryDto revenueSummaryDto = new RevenueSummaryDto();
//                revenueSummaryDto.setMonth(current.getMonth().toString());
//                revenueSummaryDto.setQuantity(revenuePerMonth.getQuantity());
//                revenueSummaryDto.setStartDate(lastDate.toLocalDate());
//                revenueSummaryDto.setEndDate(current.toLocalDate());
//                revenueSummaryDto.setTotalAmount(revenuePerMonth.getTotalAmount());
//
//                revenueSummariesList.add(revenueSummaryDto);
//            }
//            current = lastDate;
//            lastDate = lastDate.minusMonths(1);
//        }
//        System.out.println(revenueSummariesList);
    }

    @Override
    public List<BookWithAuthorsDto> getSalesTrends() {
        List<Book> salesTrends = bookRepository.findAllByInTrend();
        System.out.println(salesTrends);
        List<BookWithAuthorsDto> bookDtoList = new ArrayList<>();
        salesTrends.forEach(book -> {
            System.out.println("BOOK => ");
            System.out.println(book.toString());
            BookWithAuthorsDto bookDto = new BookWithAuthorsDto();
            bookDto.setBookId(book.getBookId());
            bookDto.setTitle(book.getTitle());
            bookDto.setGenre(book.getGenre());
            bookDto.setPrice(book.getPrice());
            bookDto.setQuantity(book.getQuantity());
            bookDto.setInTrend(book.isInTrend());
            Set<BookAuthorDto> authors = new HashSet<>();
            book.getAuthors().forEach(author -> {
                BookAuthorDto authorDto = new BookAuthorDto();
                authorDto.setFirstName(author.getFirstName());
                authorDto.setLastName(author.getLastName());
                authors.add(authorDto);
            });
            bookDto.setAuthors(authors);
            bookDtoList.add(bookDto);
        });
        return bookDtoList;
    }

    @Override
    public List<BookWithAuthorsDto> getBestSellingBooks() {
        Pageable pageable = PageRequest.of(0, 2);
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime lastDate = LocalDateTime.now().minusMonths(1);

        List<Integer> bestSellingQuantity = bookRepository.findBestSoldQuantity(lastDate, current, pageable);
        System.out.println("BEST SOLD QUANTITY");
        System.out.println(bestSellingQuantity);

        int max1 = 0;
        int max2 = 0;

        if (bestSellingQuantity.size() > 0) {
            max1 = bestSellingQuantity.get(0);
        }

        if (bestSellingQuantity.size() > 1) {
            max2 = bestSellingQuantity.get(1);
        }

        List<Book> bestSellingBooks = bookRepository.findAllBestSellingBooks(max1, max2, lastDate, current, pageable);
        System.out.println(bestSellingBooks);

        List<BookWithAuthorsDto> bookDtoList = new ArrayList<>();
        bestSellingBooks.forEach(book -> {
            System.out.println("BOOK => ");
            System.out.println(book.toString());
            BookWithAuthorsDto bookDto = new BookWithAuthorsDto();
            bookDto.setBookId(book.getBookId());
            bookDto.setTitle(book.getTitle());
            bookDto.setGenre(book.getGenre());
            bookDto.setPrice(book.getPrice());
            bookDto.setQuantity(book.getQuantity());
            bookDto.setInTrend(book.isInTrend());
            Set<BookAuthorDto> authors = new HashSet<>();
            book.getAuthors().forEach(author -> {
                BookAuthorDto authorDto = new BookAuthorDto();
                authorDto.setFirstName(author.getFirstName());
                authorDto.setLastName(author.getLastName());
                authors.add(authorDto);
            });
            bookDto.setAuthors(authors);
            bookDtoList.add(bookDto);
        });
        return bookDtoList;
    }

    @Override
    public List<InventoryLevelByGenreDto> getInventoryLevels() {
        List<InventoryLevelByGenreDto> inventoryList = bookRepository.findAllInInventoryLevelByGenre();
        System.out.println(inventoryList);
        return inventoryList;
    }

    @Override
    public List<RevenueSummaryDto> getRevenueSummary() {
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime lastDate = LocalDateTime.of(current.getYear(), current.getMonth(), 1, 0, 0, 0);

        List<RevenueSummaryDto> revenueSummariesList = new ArrayList<>();
        for (int i = 12; i >= 1; i--) {
            System.out.println("START DATE: " + lastDate + " END DATE: " + current);
            RevenuePerMonthDto revenuePerMonth = transactionRepository.revenueSummaries(lastDate, current);
            if (revenuePerMonth.getQuantity() != null && revenuePerMonth.getTotalAmount() != null) {

                System.out.println("REVENUE SUMMARIES: " + revenuePerMonth);

                RevenueSummaryDto revenueSummaryDto = new RevenueSummaryDto();
                revenueSummaryDto.setMonth(current.getMonth().toString());
                revenueSummaryDto.setQuantity(revenuePerMonth.getQuantity());
                revenueSummaryDto.setStartDate(lastDate.toLocalDate());
                revenueSummaryDto.setEndDate(current.toLocalDate());
                revenueSummaryDto.setTotalAmount(revenuePerMonth.getTotalAmount());

                revenueSummariesList.add(revenueSummaryDto);
            }
            current = lastDate.minusDays(1);
            lastDate = lastDate.minusMonths(1);
        }
        System.out.println(revenueSummariesList);
        return revenueSummariesList;
    }

    @Override
    public List<CustomReportDto> getCustomReports(String startDateString, String endDateString) {

        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        LocalDateTime startLDT = LocalDateTime.of(startDate, LocalTime.of(0, 0));
        LocalDateTime endLDT = LocalDateTime.of(endDate, LocalTime.of(0, 0));

        List<CustomReportDto> customReportsList = reportingRepository.getCustomReports(startLDT, endLDT);
        return customReportsList;
    }

    @Override
    public List<CustomReportDto> getCustomReportsByGenre(Genre genre) {
        List<CustomReportDto> customReportsList = reportingRepository.getCustomReportsByGenre(genre);
        return customReportsList;
    }
}
