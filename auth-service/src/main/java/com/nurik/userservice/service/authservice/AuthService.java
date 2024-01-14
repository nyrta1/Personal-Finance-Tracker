package com.nurik.userservice.service.authservice;

import com.nurik.userservice.payload.request.AuthRequest;
import com.nurik.userservice.payload.response.MessageResponse;
import org.springframework.http.ResponseCookie;

public interface AuthService {
    ResponseCookie authenticateUser(AuthRequest authUser);
    MessageResponse registerUser(AuthRequest authUser);
    Long getUserIdFromJwt(String jwtToken);
    String jwtToken(AuthRequest authRequest);
}
