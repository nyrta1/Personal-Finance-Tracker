package com.nurik.balanceservice.security.whitelist;

public class SecurityWhiteList {
    public static class PUBLIC_NETWORK {
        public static String[] BALANCE_INFO = {
            "/balance/user",
        };
    }

    public static class PRIVATE_NETWORK {
        public static String[] BALANCE_SYSTEM_LOGIC = {
                "/balance/user/create",
                "/balance/user/spend",
                "/balance/user/add",
                "/balance/user/jwt/validate",
        };
    }
}
