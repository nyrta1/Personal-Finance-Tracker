package com.nurik.userservice.service.userservice;

import com.nurik.userservice.models.AuthRequest;
import com.nurik.userservice.models.UserEntity;

public interface UserService {
    void registerTheUser(UserEntity user);
    UserEntity getUserEntity(AuthRequest authUser);
    boolean existsByUsername(String username);
}
