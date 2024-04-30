package com.example.bookHaven.service;

import com.example.bookHaven.dto.BookWithAuthorsDto;
import com.example.bookHaven.dto.CustomReportDto;
import com.example.bookHaven.dto.InventoryLevelByGenreDto;
import com.example.bookHaven.dto.RevenueSummaryDto;
import com.example.bookHaven.model.Book;
import com.example.bookHaven.model.Genre;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportingAndAnalyticsService {
    void reportingAndAnalytics();
    List<BookWithAuthorsDto> getSalesTrends();
    List<BookWithAuthorsDto> getBestSellingBooks();
    List<InventoryLevelByGenreDto> getInventoryLevels();
    List<RevenueSummaryDto> getRevenueSummary();
    List<CustomReportDto> getCustomReports(String startDateString, String endDateString);
    List<CustomReportDto> getCustomReportsByGenre(Genre genre);
}
