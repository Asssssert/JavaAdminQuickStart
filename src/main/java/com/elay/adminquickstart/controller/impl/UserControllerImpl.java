package com.elay.adminquickstart.controller.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.controller.UserController;
import com.elay.adminquickstart.emus.ResponseStatus;
import com.elay.adminquickstart.entity.Users;
import com.elay.adminquickstart.request.user.AdminUpdUserReq;
import com.elay.adminquickstart.response.Result;
import com.elay.adminquickstart.response.user.AdminUserResp;
import com.elay.adminquickstart.service.impl.UsersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LI
 * @since 2024/4/14
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // 允许所有来源的跨域请求，缓存时间为1小时
public class UserControllerImpl implements UserController {
    @Resource
    private UsersService usersService;

    @Override
    public Result<Void> upd(AdminUpdUserReq params) {
        return null;
    }

    @Override
    public Result<Void> del(Integer userId) {
        boolean b = usersService.removeById(userId);
        if (!b) {
            return Result.err(ResponseStatus.USER_NOT_FOUND, null);
        }
        return Result.ok(ResponseStatus.SUCCESS, null);
    }

    @Override
    public Result<AdminUserResp> get(Integer userId) {
        Users users = usersService.getById(userId);
        if (users == null) {
            return Result.err(ResponseStatus.USER_NOT_FOUND, null);
        }
        AdminUserResp data = new AdminUserResp();
        BeanUtil.copyProperties(users, data);
        return Result.ok(ResponseStatus.SUCCESS, data);
    }

    @Override
    public Result<Page> searchByKey(String key, Integer page, Integer size) {
        Page usersPage = usersService.searchByKey(key, page, size);
        if (usersPage == null) {
            return Result.err(ResponseStatus.NOT_DATA, null);
        }
        List<AdminUserResp> data = new ArrayList<>();
        //TODO:lastLoginTime,lastLoginIp
        // 封装分页数据
        usersPage.getRecords().forEach(item -> {
            AdminUserResp user = new AdminUserResp();
            BeanUtil.copyProperties(item, user);
            data.add(user);
        });
        usersPage.setRecords(data);
        return Result.ok(ResponseStatus.SUCCESS, usersPage);
    }
}
