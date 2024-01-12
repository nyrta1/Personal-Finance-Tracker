package com.nurik.transactionservice.repository;

import com.nurik.transactionservice.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository
        extends JpaRepository<Transactions, Long> {
}
