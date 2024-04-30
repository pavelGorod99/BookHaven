package com.example.bookHaven.service.impl;

import com.example.bookHaven.dto.CustomerPurchaseHistoryDto;
import com.example.bookHaven.dto.TransactionDto;
import com.example.bookHaven.dto.TransactionItemDto;
import com.example.bookHaven.model.*;
import com.example.bookHaven.repository.BookRepository;
import com.example.bookHaven.repository.BookTransactionRepository;
import com.example.bookHaven.repository.CustomerRepository;
import com.example.bookHaven.repository.TransactionRepository;
import com.example.bookHaven.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final BookTransactionRepository bookTransactionRepository;
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(BookTransactionRepository bookTransactionRepository, TransactionRepository transactionRepository, CustomerRepository customerRepository, BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookTransactionRepository = bookTransactionRepository;
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactionList = transactionRepository.findAll();
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionList.forEach(transaction -> {
            TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
            transactionDtoList.add(transactionDto);
        });
        return transactionDtoList;
    }

    @Override
    public List<CustomerPurchaseHistoryDto> getAllTransactionsByCustomer(Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        List<CustomerPurchaseHistoryDto> historyTransactionsByCustomer = transactionRepository.getTransactionByCustomerId(customerId);
        return historyTransactionsByCustomer;
    }

    @Override
    public TransactionDto saveTransaction(TransactionItemDto transactionDto) {

        System.out.println("TRANSACTION BONE: ");
        System.out.println(transactionDto.toString());

        Customer customer = customerRepository.findById(transactionDto.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        System.out.println(customer);
        Book book = bookRepository.findById(transactionDto.getBookId())
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
        System.out.println(book);
        if (book.getQuantity() >= transactionDto.getQuantity()) {

            double totalAmount = book.getPrice() * transactionDto.getQuantity();

            if (transactionDto.getDiscount() > 0) {
                totalAmount = totalAmount - ((totalAmount * transactionDto.getDiscount()) / 100);
            }

            Transaction transaction = new Transaction();
            transaction.setCustomer(customer);
            transaction.setTotalAmount(totalAmount);
            transaction.setDiscount(transactionDto.getDiscount());
            LocalDateTime transactionTime = LocalDateTime.now();
            System.out.println("TRANSACTION TIME: " + transactionTime);
            transaction.setTransactionDateTime(transactionTime);

            transaction = transactionRepository.save(transaction);
            System.out.println("NEW TRANSACTION: " + transaction);

            BookTransactionId bookTransactionId = new BookTransactionId();
            bookTransactionId.setBook(book);
            bookTransactionId.setTransaction(transaction);

            BookTransaction bookTransaction = new BookTransaction();
            bookTransaction.setId(bookTransactionId);
            bookTransaction.setQuantity(transactionDto.getQuantity());

            System.out.println("BOOK TRANSACTION: " + bookTransactionRepository.save(bookTransaction));


            TransactionDto transactionDto1 = new TransactionDto();
            transactionDto1.setTransactionId(transaction.getTransactionId());
            transactionDto1.setCustomer(customer);
            transactionDto1.setTotalAmount(totalAmount);
            transactionDto1.setTransactionDateTime(transactionTime);

            book.setQuantity(book.getQuantity() - transactionDto.getQuantity());
            bookRepository.save(book);

            return transactionDto1;
        } else throw new NoSuchElementException("Book store has only " + book.getQuantity() + " copies for this book!");
    }
}