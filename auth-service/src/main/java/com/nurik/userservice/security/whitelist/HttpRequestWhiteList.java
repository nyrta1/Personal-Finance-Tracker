package com.nurik.userservice.security.whitelist;

public class HttpRequestWhiteList {
    public final static String[] POST_AUTH_REQUEST = {
            "/users/auth/login",
            "/users/auth/register",
    };

    public final static String[] POST_BALANCE_MICROSERVICE = {
            "/users/auth/jwt-token-valid",
    };

    public final static String[] GET_ACTUATOR_INFO = {
            "/actuator",
            "/actuator/health/{*path}",
            "/actuator/health",
            "/actuator/info",
            "/actuator/prometheus"
    };
}
