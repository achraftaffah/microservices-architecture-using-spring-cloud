package com.ajincodew.transactionservice.mapper;

import com.ajincodew.transactionservice.dto.TransactionRequest;
import com.ajincodew.transactionservice.model.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-08T15:01:05+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction transactionRequestToTransaction(TransactionRequest transactionRequest) {
        if ( transactionRequest == null ) {
            return null;
        }

        Transaction.TransactionBuilder transaction = Transaction.builder();

        transaction.fromAccount( transactionRequest.getFromAccountId() );
        transaction.toAccount( transactionRequest.getToAccountId() );
        transaction.amount( transactionRequest.getAmount() );

        transaction.id( java.util.UUID.randomUUID().toString() );

        return transaction.build();
    }
}
