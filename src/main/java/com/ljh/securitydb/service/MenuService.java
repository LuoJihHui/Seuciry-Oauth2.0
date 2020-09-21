package com.ljh.securitydb.service;

import com.ljh.securitydb.entity.Menu;

import java.util.List;

/**
 * @author LuoJiaHui
 * @date 2020/9/21 10:27
 * @description
 */
public interface MenuService {

    List<Menu> listMenusWithRole();
}
