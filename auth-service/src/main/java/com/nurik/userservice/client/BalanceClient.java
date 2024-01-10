package com.nurik.userservice.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface BalanceClient {
    @PostExchange("/balance/user/create")
    void createBalance(@RequestBody Long userId);
}
