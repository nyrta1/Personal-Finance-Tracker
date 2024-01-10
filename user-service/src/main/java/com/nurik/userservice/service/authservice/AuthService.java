package com.nurik.userservice.service.authservice;

import com.nurik.userservice.models.AuthRequest;
import com.nurik.userservice.models.MessageResponse;
import org.springframework.http.ResponseCookie;

public interface AuthService {
    ResponseCookie authenticateUser(AuthRequest authUser);
    MessageResponse registerUser(AuthRequest authUser);
}
