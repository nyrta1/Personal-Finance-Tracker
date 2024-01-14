package com.nurik.userservice.controllers;

import com.nurik.userservice.exception.UserNotFoundException;
import com.nurik.userservice.payload.request.AuthRequest;
import com.nurik.userservice.payload.response.MessageResponse;
import com.nurik.userservice.security.util.SecurityUtil;
import com.nurik.userservice.service.authservice.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/auth")
@Slf4j
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authUser) {
        try {
            if (authUser.getUsername() == null || authUser.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            String jwtTokenString = authService.jwtToken(authUser);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenString)
                    .body("Successfully! " + SecurityUtil.getSessionUser() + " " + jwtTokenString);
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Username or password is invalid!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authUser) {
        try {
            if (authUser.getUsername() == null || authUser.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            MessageResponse statusResponse = authService.registerUser(authUser);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(statusResponse.getMessage());
        } catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Username or password is invalid!");
        }
    }

    @PostMapping("/jwt/extract-userid")
    public ResponseEntity<?> jwtIsValid(@RequestParam("jwtToken") String jwtToken) {
        Long userId = authService.getUserIdFromJwt(jwtToken);
        return ResponseEntity
                .status(userId == null ? HttpStatus.UNAUTHORIZED : HttpStatus.OK)
                .body(userId);
    }
}