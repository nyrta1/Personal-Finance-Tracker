package com.nurik.balanceservice.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface AuthClient {
    @PostExchange("/users/auth/jwt/extract-userid")
    Long getUserIdFromJwt(@RequestParam("jwtToken") String jwtToken);
}
