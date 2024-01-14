package com.nurik.userservice.service.userservice;

import com.nurik.userservice.payload.request.AuthRequest;
import com.nurik.userservice.models.UserEntity;

public interface UserService {
    void registerTheUser(UserEntity user);
    UserEntity getUserEntity(AuthRequest authUser);
    UserEntity findUserByUsername(String username);
    boolean existsByUsername(String username);
}
