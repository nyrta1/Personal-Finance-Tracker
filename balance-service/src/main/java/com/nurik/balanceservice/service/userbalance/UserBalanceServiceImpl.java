package com.nurik.balanceservice.service.userbalance;

import com.nurik.balanceservice.model.UserBalance;
import com.nurik.balanceservice.repository.UserBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override
    public Boolean spent(Long userId, Integer amount) {
        UserBalance userBalance = userBalanceRepository.findByUserId(userId).orElse(null);
        if (userBalance == null) {
            return false;
        }
        if (userBalance.getTotalMoney() < amount) {
            return false;
        }

        Long resultRemainsMoney = userBalance.getTotalMoney() - amount;
        userBalance.setTotalMoney(resultRemainsMoney);
        userBalanceRepository.save(userBalance);

        return true;
    }

    @Override
    public Boolean add(Long userId, Integer amount) {
        UserBalance currentUserBalance = userBalanceRepository.findByUserId(userId).orElse(null);
        if (currentUserBalance == null) {
            return false;
        }

        Long resultRemainsMoney = currentUserBalance.getTotalMoney() + amount;
        currentUserBalance.setTotalMoney(resultRemainsMoney);
        userBalanceRepository.save(currentUserBalance);

        return true;
    }
}
