package com.ajincodew.transactionservice.service.impl;

import com.ajincodew.transactionservice.client.AccountClient;
import com.ajincodew.transactionservice.dto.TransactionRequest;
import com.ajincodew.transactionservice.mapper.TransactionMapper;
import com.ajincodew.transactionservice.model.Transaction;
import com.ajincodew.transactionservice.repository.TransactionRepository;
import com.ajincodew.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper transactionMapper;
    private final AccountClient accountClient;

    @Override
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(String id) {
        return repository.findById(id);
    }

    @Override
    public Transaction createTransaction(TransactionRequest request) {
        Transaction tx = transactionMapper.transactionRequestToTransaction(request);

        // Optional: call account client before saving
        try {
            accountClient.getAccount(String.valueOf(tx.getFromAccount()));
        } catch (Exception ignored) {
        }

        return repository.save(tx);
    }

    @Override
    public void deleteTransaction(String id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}