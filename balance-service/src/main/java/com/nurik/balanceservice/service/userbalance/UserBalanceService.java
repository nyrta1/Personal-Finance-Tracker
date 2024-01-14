package com.nurik.balanceservice.service.userbalance;

import com.nurik.balanceservice.model.UserBalance;
import org.springframework.http.HttpStatus;

public interface UserBalanceService {
    void create(Long userId);
    UserBalance findByUserId(Long userId);
    Boolean spent(Long userId, Integer amount);
    Boolean add(Long userId, Integer amount);
}
