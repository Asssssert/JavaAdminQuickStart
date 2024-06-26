package com.elay.user.authority.controller.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.user.authority.controller.UserController;
import com.elay.user.authority.response.Result;
import com.elay.user.authority.response.user.AdminUserResp;
import com.elay.user.authority.service.impl.UserRolesService;
import com.elay.user.authority.service.impl.UsersService;
import com.elay.user.emus.ResponseStatus;
import com.elay.user.authority.entity.UserRoles;
import com.elay.user.authority.entity.Users;
import com.elay.user.authority.request.user.AdminUpdUserReq;
import com.elay.user.authority.request.user.UpdUserRoleReq;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource
    private UserRolesService userRolesService;

    @Override
    public Result<Void> upd(Integer userId, AdminUpdUserReq params) {
        boolean flag = usersService.upd(userId, params);
        if (flag) {
            return Result.ok(ResponseStatus.SUCCESS, null);
        }
        return Result.err(ResponseStatus.USERNAME_PASSWORD_ERROR);
    }

    @Override
    public Result<Void> del(Integer userId) {
        boolean b = usersService.delById(userId);
        if (!b) {
            return Result.err(ResponseStatus.USER_NOT_FOUND, null);
        }
        //删除用户角色关联表
        userRolesService.delByUserId(userId);
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

    @Override
    public Result<Void> updRoleIds(UpdUserRoleReq params) {
        if (usersService.getById(params.getUserId()) == null) {
            return Result.err(ResponseStatus.USER_NOT_FOUND);
        }
        if(!userRolesService.updRoleIds(params)){
            return Result.err(ResponseStatus.ROLE_EXIST);
        }
        return Result.ok(ResponseStatus.SUCCESS);
    }

    @Override
    public Result<List<Integer>> getUserIds(Integer userId) {
        List<Integer> menuIds = userRolesService.listByUserId(userId).stream().map(UserRoles::getRoleId).toList();
        return Result.ok(ResponseStatus.SUCCESS, menuIds);
    }
}
