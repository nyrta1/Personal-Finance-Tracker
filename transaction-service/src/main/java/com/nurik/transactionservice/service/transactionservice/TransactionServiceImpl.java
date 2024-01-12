package com.nurik.transactionservice.service.transactionservice;

import com.nurik.transactionservice.model.Category;
import com.nurik.transactionservice.model.Transactions;
import com.nurik.transactionservice.model.UserBalance;
import com.nurik.transactionservice.payload.request.TransactionRequest;
import com.nurik.transactionservice.repository.CategoryRepository;
import com.nurik.transactionservice.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class TransactionServiceImpl implements TransactionService {
    private final TransactionsRepository transactionsRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TransactionServiceImpl(TransactionsRepository transactionsRepository, CategoryRepository categoryRepository) {
        this.transactionsRepository = transactionsRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Transactions save(UserBalance userBalance, TransactionRequest transactionRequest) {
        Category category = categoryRepository.findByCategoryName(
                transactionRequest.getCategory()).orElse(null);

        Transactions transactions = Transactions.builder()
                .userId(userBalance.getUserId())
                .category(category)
                .amount(transactionRequest.getAmount())
                .description(transactionRequest.getDescription())
                .status(transactionRequest.getStatus())
                .build();

        transactionsRepository.saveAndFlush(transactions);

        return transactions;
    }
}
