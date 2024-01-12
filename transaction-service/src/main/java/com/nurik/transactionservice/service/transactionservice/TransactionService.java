package com.nurik.transactionservice.service.transactionservice;

import com.nurik.transactionservice.model.Transactions;
import com.nurik.transactionservice.model.UserBalance;
import com.nurik.transactionservice.payload.request.TransactionRequest;

public interface TransactionService {
    Transactions save(UserBalance userBalance, TransactionRequest transactionRequest);
}
