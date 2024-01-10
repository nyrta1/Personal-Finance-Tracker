package com.nurik.userservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface BalanceClient {
    @PostExchange("/balance/auth")
    ResponseEntity<?> authTheUser(@RequestBody Authentication authentication);
}
