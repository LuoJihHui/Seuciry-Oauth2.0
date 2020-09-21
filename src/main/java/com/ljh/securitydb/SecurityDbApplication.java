package com.ljh.securitydb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.ljh.securitydb.mapper")
@EnableCaching
public class SecurityDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDbApplication.class, args);
    }

}
