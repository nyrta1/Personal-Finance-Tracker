package com.nurik.balanceservice.controller;

import com.nurik.balanceservice.client.AuthClient;
import com.nurik.balanceservice.model.UserBalance;
import com.nurik.balanceservice.service.userbalance.UserBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance/user")
@Slf4j
public class UserBalanceController {
    private final UserBalanceService userBalanceService;
    private final AuthClient authClient;

    @Autowired
    public UserBalanceController(UserBalanceService userBalanceService, AuthClient authClient) {
        this.userBalanceService = userBalanceService;
        this.authClient = authClient;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBalance(@RequestBody Long userId) {
        userBalanceService.create(userId);
        log.info("The registered userId is: {}", userId);
        return ResponseEntity.status(HttpStatus.OK).body("The registered userId is: " + userId);
    }

    @PostMapping("/spend")
    public ResponseEntity<Boolean> spentMoneyFromBalance(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount) {
        Boolean status = userBalanceService.spent(userId, amount);
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(status);
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addMoneyToBalance(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount) {
        Boolean status = userBalanceService.add(userId, amount);
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(status);
    }

    @PostMapping("/jwt/validate")
    public ResponseEntity<?> validate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        Long userId = authClient.getUserIdFromJwt(authorizationHeader);
        return ResponseEntity
                .status(userId == null ? HttpStatus.UNAUTHORIZED : HttpStatus.OK)
                .body(userId);
    }

    @GetMapping
    public ResponseEntity<UserBalance> getUserBalance(@RequestParam("userId") Long userId) {
        UserBalance userBalance = userBalanceService.findByUserId(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userBalance);
    }
}
