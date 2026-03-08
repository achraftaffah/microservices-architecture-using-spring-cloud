package com.ajincodew.transactionservice.controller.impl;

import com.ajincodew.transactionservice.controller.TransactionController;
import com.ajincodew.transactionservice.dto.TransactionRequest;
import com.ajincodew.transactionservice.model.Transaction;
import com.ajincodew.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @Override
    public ResponseEntity<Transaction> getTransaction(String id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Transaction> createTransaction(TransactionRequest req) {
        Transaction tx = transactionService.createTransaction(req);
        return ResponseEntity.ok(tx);
    }

    @Override
    public ResponseEntity<Void> deleteTransaction(String id) {
        if (transactionService.getTransactionById(id).isPresent()) {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}