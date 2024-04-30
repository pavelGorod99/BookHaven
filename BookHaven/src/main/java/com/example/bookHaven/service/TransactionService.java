package com.example.bookHaven.service;

import com.example.bookHaven.dto.CustomerDto;
import com.example.bookHaven.dto.CustomerPurchaseHistoryDto;
import com.example.bookHaven.dto.TransactionDto;
import com.example.bookHaven.dto.TransactionItemDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAllTransactions();
    List<CustomerPurchaseHistoryDto> getAllTransactionsByCustomer(Long id);
    TransactionDto saveTransaction(TransactionItemDto transactionDto);
}
