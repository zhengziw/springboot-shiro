package com.shiro.service;

import com.shiro.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzw
 * @since 2020-11-29
 */
public interface IUserService extends IService<User> {

    User getByUserName(String username);
    User getUserWithRoleMenuById(Integer userId);
}
