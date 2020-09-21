package com.ljh.securitydb.config;

import com.ljh.securitydb.entity.Menu;
import com.ljh.securitydb.entity.Role;
import com.ljh.securitydb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author LuoJiaHui
 * @date 2020/9/21 10:23
 * @description
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 路径匹配符
     *
     * @auth LuoJiaHui
     * @Date 2020/9/21 10:25
     **/
    AntPathMatcher matcher = new AntPathMatcher();

    @Autowired
    private MenuService menuService;

    /**
     * 分析资源所需角色
     *
     * @param object
     * @return java.util.Collection<org.springframework.security.access.ConfigAttribute>
     * @auth LuoJiaHui
     * @Date 2020/9/21 10:24
     **/
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menus = menuService.listMenusWithRole();
        for (Menu menu : menus) {
            // 匹配当前请求路径和资源路径
            if (matcher.match(menu.getPattern(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] needRoles = new String[size];
                for (int i = 0; i < size; i++) {
                    needRoles[i] = roles.get(i).getName();
                }
                // 返回资源所需角色
                return SecurityConfig.createList(needRoles);
            }
        }
        // 标记未匹配上的路径
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
