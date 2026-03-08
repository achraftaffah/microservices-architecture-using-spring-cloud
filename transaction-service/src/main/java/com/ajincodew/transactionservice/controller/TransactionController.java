package com.ajincodew.transactionservice.controller;

import com.ajincodew.transactionservice.dto.TransactionRequest;
import com.ajincodew.transactionservice.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TransactionController {
    @GetMapping
    List<Transaction> getAllTransactions();

    @GetMapping("/{id}")
    ResponseEntity<Transaction> getTransaction(@PathVariable String id);

    @PostMapping
    ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest req);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTransaction(@PathVariable String id);
}