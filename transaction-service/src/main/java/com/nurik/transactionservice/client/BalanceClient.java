package com.nurik.transactionservice.client;

import com.nurik.transactionservice.model.UserBalance;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface BalanceClient {
    @GetExchange("/balance/user")
    UserBalance getUserBalance(@RequestHeader HttpHeaders headers);
}
