package com.nurik.transactionservice.client;

import com.nurik.transactionservice.model.UserBalance;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface BalanceClient {
    @GetExchange("/balance/user")
    UserBalance getUserBalance(@RequestParam("userId") Long userId);

    @PostExchange("/balance/user/spend")
    Boolean spend(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount);

    @PostExchange("/balance/user/add")
    Boolean add(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount);
}
