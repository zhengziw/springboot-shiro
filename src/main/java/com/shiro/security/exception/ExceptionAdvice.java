package com.shiro.security.exception;

import com.shiro.security.pojo.RestResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zzw
 * @version 1.0
 * @date 2020-11-29 15:20
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    public RestResponse unauthenticatedException() {
        RestResponse builder = new RestResponse("对不起,您还未登录!",false,403);
        return builder;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public RestResponse unauthorizedException() {
        RestResponse builder = new RestResponse("对不起,您没权限操作!",false,401);
        return builder;
    }

    @ExceptionHandler(UnknownAccountException.class)
    @ResponseBody
    public RestResponse unknownAccountException() {
        RestResponse builder = new RestResponse("登陆失败,用户名或密码错误!",false,403);
        return builder;
    }
}
