package com.nurik.userservice.controllers;

import com.nurik.userservice.exception.UserNotFoundException;
import com.nurik.userservice.models.UserEntity;
import com.nurik.userservice.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserEntity user) {
        try {
            if (user.getUsername() == null || user.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            UserEntity userData = userService.getUserEntityByNameAndPassword(user.getUsername(), user.getPassword());
            if (userData == null) {
                throw new UserNotFoundException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
        try {
            if (user.getUsername() == null || user.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            UserEntity userData = userService.getUserEntityByNameAndPassword(user.getUsername(), user.getPassword());
            if (userData != null) {
                return new ResponseEntity<>("The username exists", HttpStatus.CONFLICT);
            }
            userService.registerTheUser(user);
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
