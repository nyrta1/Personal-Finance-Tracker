package com.nurik.userservice.service.userservice;

import com.nurik.userservice.models.AuthRequest;
import com.nurik.userservice.models.Role;
import com.nurik.userservice.models.UserEntity;
import com.nurik.userservice.repository.RoleRepository;
import com.nurik.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerTheUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserEntity(AuthRequest authUser) {
        Optional<UserEntity> findUserEntity = userRepository.findByUsername(authUser.getUsername());
        return findUserEntity.orElse(null);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
