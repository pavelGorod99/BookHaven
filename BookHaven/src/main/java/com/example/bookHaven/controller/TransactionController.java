package com.example.bookHaven.controller;

import com.example.bookHaven.dto.CustomerPurchaseHistoryDto;
import com.example.bookHaven.dto.TransactionDto;
import com.example.bookHaven.dto.TransactionItemDto;
import com.example.bookHaven.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // END-POINT FOR ADMIN
    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // END-POINT FOR STUFF AND ADMIN
    @GetMapping("/by-customer")
    public ResponseEntity<List<CustomerPurchaseHistoryDto>> getAllTransactionHistoryByCustomer(@RequestParam Long customerId) {
        List<CustomerPurchaseHistoryDto> transactionDtoList = transactionService.getAllTransactionsByCustomer(customerId);
        return ResponseEntity.ok(transactionDtoList);
    }

    // END-POINT FOR STUFF AND CUSTOMERS AND ADMIN
    @PostMapping("/")
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody TransactionItemDto transactionDto) {
        TransactionDto transactionDto1 = transactionService.saveTransaction(transactionDto);
        return ResponseEntity.ok(transactionDto1);
    }
}
