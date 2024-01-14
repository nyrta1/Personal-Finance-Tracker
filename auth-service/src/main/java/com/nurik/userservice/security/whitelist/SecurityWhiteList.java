package com.nurik.userservice.security.whitelist;

public class SecurityWhiteList {
    public static class PUBLIC_NETWORK {
        public static class AUTH_REQUEST {
            public final static String[] GET = {};
            public final static String[] POST = {
                    "/users/auth/login",
                    "/users/auth/register",
            };

            public final static String[] PUT = {};
            public final static String[] DELETE = {};
        }
    }

    public static class PRIVATE_NETWORK {
        public static class BALANCE_MICROSERVICE {
            public final static String[] GET = {};
            public final static String[] POST = {
                    "/users/auth/jwt/extract-userid",
            };

            public final static String[] PUT = {};
            public final static String[] DELETE = {};
        }

        public static class ACTUATOR_INFO {
            public final static String[] GET = {
                    "/actuator",
                    "/actuator/health/{*path}",
                    "/actuator/health",
                    "/actuator/info",
                    "/actuator/prometheus"
            };
            public final static String[] POST = {};

            public final static String[] PUT = {};
            public final static String[] DELETE = {};
        }
    }
}
