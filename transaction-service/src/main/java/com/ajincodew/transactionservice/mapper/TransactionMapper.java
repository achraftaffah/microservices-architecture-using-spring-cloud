package com.ajincodew.transactionservice.mapper;

import com.ajincodew.transactionservice.dto.TransactionRequest;
import com.ajincodew.transactionservice.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "fromAccount", source = "fromAccountId")
    @Mapping(target = "toAccount", source = "toAccountId")
    Transaction transactionRequestToTransaction(TransactionRequest transactionRequest);
}