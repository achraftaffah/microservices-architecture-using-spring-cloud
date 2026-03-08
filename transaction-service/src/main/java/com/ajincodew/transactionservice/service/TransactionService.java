package com.ajincodew.transactionservice.service;

import com.ajincodew.transactionservice.dto.TransactionRequest;
import com.ajincodew.transactionservice.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    Optional<Transaction> getTransactionById(String id);

    Transaction createTransaction(TransactionRequest request);

    void deleteTransaction(String id);
}