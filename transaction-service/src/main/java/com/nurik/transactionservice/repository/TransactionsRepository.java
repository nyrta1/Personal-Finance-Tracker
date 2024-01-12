package com.nurik.transactionservice.repository;

import com.nurik.transactionservice.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository
        extends JpaRepository<Transactions, Long> {
}
