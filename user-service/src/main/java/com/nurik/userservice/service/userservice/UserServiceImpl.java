package com.nurik.userservice.service.userservice;

import com.nurik.userservice.models.AuthRequest;
import com.nurik.userservice.models.Role;
import com.nurik.userservice.models.UserEntity;
import com.nurik.userservice.repository.RoleRepository;
import com.nurik.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerTheUser(AuthRequest authUser) {
        String encryptedPassword = passwordEncoder.encode(authUser.getPassword());
        Role userRole = roleRepository.findByName(Role.RoleType.USER.name());
        UserEntity registerUser = UserEntity.builder()
                .id(null)
                .username(authUser.getUsername())
                .password(encryptedPassword)
                .roles(List.of(userRole))
                .build();
        userRepository.save(registerUser);
    }

    @Override
    public UserEntity getUserEntity(AuthRequest authUser) {
        Optional<UserEntity> findUserEntity = userRepository.findByUsername(authUser.getUsername());
        String encryptedPassword = passwordEncoder.encode(authUser.getPassword());
        return findUserEntity.filter(u -> u
                .getPassword()
                        .equals(encryptedPassword))
                .orElse(null);
    }

}
