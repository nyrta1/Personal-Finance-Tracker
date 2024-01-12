package com.nurik.transactionservice.controller;

import com.nurik.transactionservice.client.BalanceClient;
import com.nurik.transactionservice.model.UserBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {
    private final BalanceClient balanceClient;

    @Autowired
    public TransactionController(BalanceClient balanceClient) {
        this.balanceClient = balanceClient;
    }

    @PostMapping("/user/balance")
    public ResponseEntity<UserBalance> getUserBalance(@RequestHeader HttpHeaders headers) {
        UserBalance userBalance = balanceClient.getUserBalance(headers);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userBalance);
    }
}
