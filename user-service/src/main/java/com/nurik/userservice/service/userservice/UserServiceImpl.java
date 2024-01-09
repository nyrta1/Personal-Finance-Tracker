package com.nurik.userservice.service.userservice;

import com.nurik.userservice.models.UserEntity;
import com.nurik.userservice.repository.UserRepository;
import com.nurik.userservice.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerTheUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity getUserEntity(UserEntity user) {
        Optional<UserEntity> findUserEntity = userRepository.findByUsername(user.getUsername());

        return findUserEntity.filter(u -> u
                .getPassword()
                        .equals(user.getPassword()))
                .orElse(null);
    }

}
