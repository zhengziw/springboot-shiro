package com.shiro.security.auth;

import com.shiro.entity.Menu;
import com.shiro.entity.Role;
import com.shiro.entity.User;
import com.shiro.security.pojo.JwtToken;
import com.shiro.service.IUserService;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import com.shiro.security.utils.JwtUtil;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zzw
 * @version 1.0
 * @date 2020-11-29 15:11
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IUserService iUserService;

    /**
     * 设置realm支持的authenticationToken类型
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return null != token && token instanceof JwtToken;
    }

    /**
     * 登陆认证
     *
     * @param authenticationToken jwtFilter传入的token
     * @return 登陆信息
     * @throws AuthenticationException 未登陆抛出异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //getCredentials getPrincipal getToken 都是返回jwt生成的token串
        String token = (String) authenticationToken.getCredentials();
        //判断token是否可用
        String username = JwtUtil.getUserName(token);
        if (username == null) {
            throw new AccountException("token invalid");
        }
        User loginUser = iUserService.getByUserName(username);
        if (!JwtUtil.verify(username, loginUser.getPassword(), token)) {
            throw new UnknownAccountException("Username or password error");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    /**
     * 授权认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = principalCollection.toString();
        //根据token获取权限授权
        String userName = JwtUtil.getUserName(token);
        User loginUser = iUserService.getByUserName(userName);
        //查询用户角色、权限
        User userWithRoleMenuById = iUserService.getUserWithRoleMenuById(loginUser.getId());
       //设置当前用户的角色
        Set<String> roles=new HashSet() ;
        for (Role role : userWithRoleMenuById.getRoles()) {
            roles.add(role.getName());
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        //设置当前用户的权限
        Set<String> menus=new HashSet<String>() ;
        for (Menu menu : userWithRoleMenuById.getMenus()) {
            menus.add(menu.getPermission());
        }
        authorizationInfo.setStringPermissions(menus);
        return authorizationInfo;
    }
}
