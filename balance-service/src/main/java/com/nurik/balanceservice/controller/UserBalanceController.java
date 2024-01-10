package com.nurik.balanceservice.controller;

import com.nurik.balanceservice.service.userbalance.UserBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance/user")
@Slf4j
public class UserBalanceController {
    private final UserBalanceService userBalanceService;

    @Autowired
    public UserBalanceController(UserBalanceService userBalanceService) {
        this.userBalanceService = userBalanceService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBalance(@RequestBody Long userId) {
        userBalanceService.create(userId);
        log.info("The registered userId is: {}", userId);
        return ResponseEntity.status(HttpStatus.OK).body("The registered userId is: " + userId);
    }
}
