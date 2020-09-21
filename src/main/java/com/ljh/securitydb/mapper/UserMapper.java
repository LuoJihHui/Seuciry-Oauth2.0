package com.ljh.securitydb.mapper;

import com.ljh.securitydb.entity.Role;
import com.ljh.securitydb.entity.User;

import java.util.List;

/**
 * @author LuoJiaHui
 * @date 2020/9/21 9:09
 * @description
 */
public interface UserMapper {


    User loadUserByUsername(String userName);

    List<Role> loadRoleByUserId(Integer userId);
}
