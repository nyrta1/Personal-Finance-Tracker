package com.nurik.transactionservice.security.whitelist;

public class SecurityWhiteList {
    public static class PUBLIC_NETWORK {
        public static class TRANSACTION_API {
            public final static String[] GET = {};
            public final static String[] POST = {
                    "/transaction/user/balance",
                    "/transaction/create",
            };

            public final static String[] PUT = {};
            public final static String[] DELETE = {};
        }

        public static class CATEGORY_API {
            public final static String[] GET = {};
            public final static String[] POST = {
                    "/category/create",
            };

            public final static String[] PUT = {};
            public final static String[] DELETE = {};
        }
    }

    public static class PRIVATE_NETWORK {

    }
}
