package com.nurik.balanceservice.service.userbalance;

import com.nurik.balanceservice.model.UserBalance;
import com.nurik.balanceservice.repository.UserBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserBalanceServiceImpl implements UserBalanceService {
    private final UserBalanceRepository userBalanceRepository;

    @Autowired
    public UserBalanceServiceImpl(UserBalanceRepository userBalanceRepository) {
        this.userBalanceRepository = userBalanceRepository;
    }

    @Override
    public void create(Long userId) {
        UserBalance userBalance = UserBalance.builder()
                .userId(userId)
                .totalMoney(0L)
                .build();

        userBalanceRepository.save(userBalance);
    }

    @Override
    public UserBalance findByUserId(Long userId) {
        return userBalanceRepository.findByUserId(userId).orElse(null);
    }
}
