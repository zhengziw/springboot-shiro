package com.shiro.service.impl;

import com.shiro.entity.User;
import com.shiro.mapper.UserMapper;
import com.shiro.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzw
 * @since 2020-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User getByUserName(String username) {
        return userMapper.getByUserName(username);
    }

    @Override
    public User getUserWithRoleMenuById(Integer userId) {
        return userMapper.getUserWithRoleMenuById(userId);
    }
}
