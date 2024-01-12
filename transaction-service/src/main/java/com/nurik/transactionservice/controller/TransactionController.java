package com.nurik.transactionservice.controller;

import com.nurik.transactionservice.client.AuthClient;
import com.nurik.transactionservice.client.BalanceClient;
import com.nurik.transactionservice.model.TransactionStatus;
import com.nurik.transactionservice.model.Transactions;
import com.nurik.transactionservice.model.UserBalance;
import com.nurik.transactionservice.payload.request.TransactionRequest;
import com.nurik.transactionservice.service.transactionservice.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {
    private final BalanceClient balanceClient;
    private final AuthClient authClient;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(BalanceClient balanceClient, AuthClient authClient, TransactionService transactionService) {
        this.balanceClient = balanceClient;
        this.authClient = authClient;
        this.transactionService = transactionService;
    }

    @PostMapping("/user/balance")
    public ResponseEntity<UserBalance> getUserBalance(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        Long currentUserId = authClient.getUserIdFromJwt(authorizationHeader);
        UserBalance userBalance = balanceClient.getUserBalance(currentUserId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userBalance);
    }

    @PostMapping("/create")
    public ResponseEntity<Transactions> getTransaction(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody TransactionRequest transactionRequest) {
        Long currentUserId = authClient.getUserIdFromJwt(authorizationHeader);
        UserBalance userBalance = balanceClient.getUserBalance(currentUserId);
        if (!getStatusFromBalanceService(currentUserId, transactionRequest.getAmount(), transactionRequest.getStatus())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        Transactions transactions = transactionService.save(userBalance, transactionRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactions);
    }

    private boolean getStatusFromBalanceService(Long currentUserId, Integer amount, TransactionStatus status) {
        if (status == TransactionStatus.ADD) {
            return balanceClient.add(currentUserId, amount);
        }
        if (status == TransactionStatus.SPEND) {
            return balanceClient.spend(currentUserId, amount);
        }
        return false;
    }
}
