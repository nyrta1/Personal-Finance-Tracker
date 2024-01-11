package com.nurik.userservice.service.userservice;

import com.nurik.userservice.client.BalanceClient;
import com.nurik.userservice.models.AuthRequest;
import com.nurik.userservice.models.UserEntity;
import com.nurik.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BalanceClient balanceClient;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BalanceClient balanceClient) {
        this.userRepository = userRepository;
        this.balanceClient = balanceClient;
    }

    @Override
    public void registerTheUser(UserEntity user) {
        UserEntity userEntity = userRepository.saveAndFlush(user);
        balanceClient.createBalance(userEntity.getId());
    }

    @Override
    public UserEntity getUserEntity(AuthRequest authUser) {
        Optional<UserEntity> findUserEntity = userRepository.findByUsername(authUser.getUsername());
        return findUserEntity.orElse(null);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        Optional<UserEntity> findUserEntity = userRepository.findByUsername(username);
        return findUserEntity.orElse(null);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
