package com.shiro.controller;

import com.shiro.security.constant.OAuthConstant;
import com.shiro.security.pojo.RestResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzw
 * @version 1.0
 * @date 2020-11-29 15:31
 */
@RestController
public class RestApiController {
    @GetMapping("/find")
    @RequiresPermissions(OAuthConstant.Permissions.SELECT)
    public RestResponse find() {
        return new RestResponse("find success",true,"find success",200);
    }

    @GetMapping("/list")
    @RequiresRoles(OAuthConstant.Roles.USER)
    public RestResponse list() {
        return  new RestResponse("list success",true,"list success",200);
    }

    @GetMapping("/count")
    @RequiresRoles(OAuthConstant.Roles.ADMIN)
    public RestResponse count() {
        return  new RestResponse("list success",true,"list success",200);
    }
}
