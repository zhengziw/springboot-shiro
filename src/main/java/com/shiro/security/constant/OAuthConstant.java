package com.shiro.security.constant;

/**
 * @author zzw
 * @version 1.0
 * @date 2020-11-29 15:32
 */
public interface OAuthConstant {

    interface Roles {
        String USER = "user";
        String ADMIN = "admin";
    }

    interface Permissions {
        String SELECT = "select";
        String INSERT = "insert";

    }

    interface Code {
        int OK = 200;
        int UNAUTHENTICATED = 401;
        int UNAUTHORIZED = 403;
        int NOT_FOUND = 404;
        int SERVER_ERROR = 500;
        int BUSINESS_ERROR = 600;
    }
}
