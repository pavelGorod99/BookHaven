package com.example.bookHaven.controller;

import com.example.bookHaven.dto.BookWithAuthorsDto;
import com.example.bookHaven.dto.CustomReportDto;
import com.example.bookHaven.dto.InventoryLevelByGenreDto;
import com.example.bookHaven.dto.RevenueSummaryDto;
import com.example.bookHaven.model.Genre;
import com.example.bookHaven.service.ReportingAndAnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportingAndAnalyticsController {

    private final ReportingAndAnalyticsService analyticsService;

    public ReportingAndAnalyticsController(ReportingAndAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // END-POINT FOR STUFF AND ADMIN AND CUSTOMER
    @GetMapping("/sales-trends")
    public ResponseEntity<List<BookWithAuthorsDto>> getAllSalesTrends() {
        List<BookWithAuthorsDto> salesTrends = analyticsService.getSalesTrends();
        return ResponseEntity.ok(salesTrends);
    }

    // END-POINT FOR STUFF AND ADMIN AND CUSTOMER
    @GetMapping("/best-selling-books")
    public ResponseEntity<List<BookWithAuthorsDto>> getAllBestSellingBooks() {
        List<BookWithAuthorsDto> salesTrends = analyticsService.getBestSellingBooks();
        return ResponseEntity.ok(salesTrends);
    }

    // END-POINT FOR STUFF AND ADMIN AND CUSTOMER
    @GetMapping("/inventory-level")
    public ResponseEntity<List<InventoryLevelByGenreDto>> getInventoryLevels() {
        List<InventoryLevelByGenreDto> inventoryLevels = analyticsService.getInventoryLevels();
        return ResponseEntity.ok(inventoryLevels);
    }

    // END-POINT FOR STUFF AND ADMIN
    @GetMapping("/revenue-summary")
    public ResponseEntity<List<RevenueSummaryDto>> getRevenueSummary() {
        List<RevenueSummaryDto> revenueSummary = analyticsService.getRevenueSummary();
        return ResponseEntity.ok(revenueSummary);
    }

    // END-POINT FOR STUFF AND ADMIN
    @GetMapping("/custom-report")
    public ResponseEntity<List<CustomReportDto>> getCustomReports(@RequestParam String startDate, @RequestParam String endDate) {
        List<CustomReportDto> customReport = analyticsService.getCustomReports(startDate, endDate);
        return ResponseEntity.ok(customReport);
    }

    // END-POINT FOR STUFF AND ADMIN
    @GetMapping("/custom-report/by-genre")
    public ResponseEntity<List<CustomReportDto>> getCustomReportsByGenre(@RequestParam Genre genre) {
        List<CustomReportDto> customReport = analyticsService.getCustomReportsByGenre(genre);
        return ResponseEntity.ok(customReport);
    }
}
