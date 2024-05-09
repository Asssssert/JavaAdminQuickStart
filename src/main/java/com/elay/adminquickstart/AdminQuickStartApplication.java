package com.elay.adminquickstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.elay.adminquickstart.authority.mapper")
public class AdminQuickStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminQuickStartApplication.class, args);
    }

}
