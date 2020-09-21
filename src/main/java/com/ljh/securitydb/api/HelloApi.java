package com.ljh.securitydb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LuoJiaHui
 * @date 2020/9/21 9:33
 * @description
 */
@RestController
public class HelloApi {

    @GetMapping("/admin/hello")
    public String hello() {
        return "Security自定义数据库认证-admin";
    }

    @GetMapping("/hello")
    public String hello4() {
        return "Security自定义数据库认证";
    }

    @GetMapping("/db/hello")
    public String hello2() {
        return "Security自定义数据库认证2-dba";
    }

    @GetMapping("/user/hello")
    public String hello3() {
        return "Security自定义数据库认证3-user";
    }
}
