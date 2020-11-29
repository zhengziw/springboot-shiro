package com.shiro.service.impl;

import com.shiro.entity.Menu;
import com.shiro.mapper.MenuMapper;
import com.shiro.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
