package com.nurik.balanceservice.repository;

import com.nurik.balanceservice.model.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository
        extends JpaRepository<UserBalance, Long> {
}
