package com.elay.adminquickstart.controller;


import com.elay.adminquickstart.service.impl.UsersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表
 前端控制器
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    private UsersService usersService;
    @GetMapping("/test")
    public Object test() {
        return usersService.list();
    }
}

