package com.shiro.mapper;

import com.shiro.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzw
 * @since 2020-11-29
 */
public interface UserMapper extends BaseMapper<User> {

    User getByUserName(@Param("username") String username);
    User getUserWithRoleMenuById(@Param("userId") Integer userId);
}
