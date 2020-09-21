package com.ljh.securitydb.service.impl;

import com.ljh.securitydb.entity.User;
import com.ljh.securitydb.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LuoJiaHui
 * @date 2020/9/21 9:08
 * @description
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        user.setRoles(userMapper.loadRoleByUserId(user.getId()));
        return user;
    }
}
