package com.shiro.security.utils;

/**
 * @author zzw
 * @version 1.0
 * @date 2020-11-29 16:59
 */
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtil {
    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    private static final String CLAIM_NAME = "username";
    /**
     *
     * 生成token
     */
    public static String createToken(String username, String password) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //加密处理密码
        Algorithm algorithm = Algorithm.HMAC256(password);
        return JWT.create()
                .withClaim(CLAIM_NAME, username)
                .withExpiresAt(date)
                .sign(algorithm);
    }
    /**
     *
     * 验证token
     */

    public static boolean verify(String username, String dbPwd, String token) {
        Algorithm algorithm = Algorithm.HMAC256(dbPwd);
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .withClaim(CLAIM_NAME, username).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * 从token中获取用户名
     */

    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(CLAIM_NAME).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}