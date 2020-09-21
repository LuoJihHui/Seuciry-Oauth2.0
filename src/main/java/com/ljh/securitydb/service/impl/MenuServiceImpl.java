package com.ljh.securitydb.service.impl;

import com.ljh.securitydb.entity.Menu;
import com.ljh.securitydb.mapper.MenuMapper;
import com.ljh.securitydb.service.MenuService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LuoJiaHui
 * @date 2020/9/21 10:27
 * @description
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    @Cacheable(cacheNames = "menus:")
    public List<Menu> listMenusWithRole() {
        return menuMapper.queryAllMenu();
    }
}
