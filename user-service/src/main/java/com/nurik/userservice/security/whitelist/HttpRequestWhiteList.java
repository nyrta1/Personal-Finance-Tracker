package com.nurik.userservice.security.whitelist;

public class HttpRequestWhiteList {
    public final static String[] AUTH_REQUEST = {
            "/users/auth/login",
            "/users/auth/register",
            "/users/auth/hello"
    };
}
