package com.nurik.userservice.service.userservice;

import com.nurik.userservice.models.AuthRequest;
import com.nurik.userservice.models.UserEntity;

public interface UserService {
    void registerTheUser(AuthRequest user);
    UserEntity getUserEntity(AuthRequest authUser);
}
