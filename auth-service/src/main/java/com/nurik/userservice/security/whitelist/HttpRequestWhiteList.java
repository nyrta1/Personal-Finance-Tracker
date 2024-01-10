package com.nurik.userservice.security.whitelist;

public class HttpRequestWhiteList {
    public final static String[] AUTH_REQUEST = {
            "/users/auth/login",
            "/users/auth/register",
            "/users/auth/hello"
    };

    public final static String[] ACTUATOR_INFO = {
            "/actuator",
            "/actuator/health/{*path}",
            "/actuator/health",
            "/actuator/info",
            "/actuator/prometheus"
    };
}
