package com.nurik.userservice.controllers;

import com.nurik.userservice.exception.UserNotFoundException;
import com.nurik.userservice.models.AuthRequest;
import com.nurik.userservice.models.UserEntity;
import com.nurik.userservice.security.jwt.JwtUtils;
import com.nurik.userservice.service.userservice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/auth")
@Slf4j
public class AuthController {
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(JwtUtils jwtUtils, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/hello")
    public ResponseEntity<HttpStatus> hello() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> loginUser(@RequestBody AuthRequest authUser) {
        log.error("HELLO FROM LOGIN POST");
        try {
            if (authUser.getUsername() == null || authUser.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            UserEntity userData = userService.getUserEntity(authUser);
            if (userData == null) {
                throw new UserNotFoundException("UserName or Password is Invalid");
            }
            Authentication authentication = getAuthentication(userData);
            String authJwtToken = jwtUtils.generateJwtToken(authentication);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody AuthRequest authUser) {
        log.error("HELLO FROM REGISTER POST");
        try {
            if (authUser.getUsername() == null || authUser.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            UserEntity userData = userService.getUserEntity(authUser);
            if (userData != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            userService.registerTheUser(authUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    private Authentication getAuthentication(UserEntity user) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }
}