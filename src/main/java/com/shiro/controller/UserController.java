package com.shiro.controller;


import com.shiro.entity.User;
import com.shiro.security.pojo.RestResponse;
import com.shiro.service.IUserService;
import com.shiro.security.utils.JwtUtil;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzw
 * @since 2020-11-29
 */
@RestController
@RequestMapping("/shiro/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @PostMapping("/login")
    public RestResponse login(String username,String password) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            RestResponse builder = new RestResponse("账号和密码不能为空!",false,403);

            return builder;
        }
        User user = iUserService.getByUserName(username);

        if (null == user || !password.equals(user.getPassword())) {
            throw new UnknownAccountException("用户名和密码错误");
        }
        String msg = "登录成功,请妥善保管您的token,有效期5分钟!";
        return new RestResponse(msg,true, JwtUtil.createToken(username, password),200);
    }

}
