package com.elay.adminquickstart.controller.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.controller.RoleController;
import com.elay.adminquickstart.emus.ResponseStatus;
import com.elay.adminquickstart.entity.RoleMenus;
import com.elay.adminquickstart.entity.RolePermissions;
import com.elay.adminquickstart.entity.Roles;
import com.elay.adminquickstart.request.role.AddRoleReq;
import com.elay.adminquickstart.request.role.UpdRoleMenuReq;
import com.elay.adminquickstart.request.role.UpdRolePermissionReq;
import com.elay.adminquickstart.request.role.UpdRoleReq;
import com.elay.adminquickstart.response.Result;
import com.elay.adminquickstart.service.impl.RoleMenusService;
import com.elay.adminquickstart.service.impl.RolePermissionsService;
import com.elay.adminquickstart.service.impl.RolesService;
import com.elay.adminquickstart.service.impl.UserRolesService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LI
 * @since 2024/4/15
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // 允许所有来源的跨域请求，缓存时间为1小时
public class RoleControllerImpl implements RoleController {
    @Resource
    private RolesService rolesService;
    @Resource
    private UserRolesService userRolesService;
    @Resource
    private RoleMenusService roleMenusService;
    @Resource
    private RolePermissionsService rolePermissionsService;

    @Override
    public Result<Void> add(AddRoleReq params) {
        if (!rolesService.add(params)) {
            return Result.err(ResponseStatus.ROLE_EXIST);
        }
        return Result.ok(ResponseStatus.SUCCESS);
    }

    @Override
    public Result<Void> upd(UpdRoleReq params) {
        if (!rolesService.upd(params)) {
            return Result.err(ResponseStatus.ROLE_EXIST);
        }
        return Result.ok(ResponseStatus.SUCCESS);
    }

    @Override
    public Result<Void> del(Integer roleId) {
        if (!rolesService.removeById(roleId)) {
            return Result.err(ResponseStatus.ROLE_NOT_EXIST);
        }
        //删除关联关系
        userRolesService.delByRoleId(roleId);
        roleMenusService.delByRoleId(roleId);
        rolePermissionsService.delByRoleId(roleId);
        return Result.ok(ResponseStatus.SUCCESS);
    }

    @Override
    public Result<Roles> get(Integer roleId) {
        Roles roles = rolesService.getById(roleId);
        if (roles != null) {
            return Result.ok(ResponseStatus.SUCCESS, roles);
        }
        return Result.err(ResponseStatus.ROLE_NOT_EXIST, null);
    }

    @Override
    public Result<Page> page(Integer page, Integer size) {
        Page<Roles> pageInfo = rolesService.page(new Page<>(page, size));
        if (pageInfo != null) {
            return Result.ok(ResponseStatus.SUCCESS, pageInfo);
        }
        return Result.err(ResponseStatus.NOT_DATA, null);
    }

    @Override
    public Result<List<Roles>> list() {
        List<Roles> rolesList = rolesService.list();
        if (rolesList == null) {
            return Result.err(ResponseStatus.NOT_DATA, null);
        }
        return Result.ok(ResponseStatus.SUCCESS, rolesList);
    }

    @Override
    public Result<Void> updPermission(UpdRolePermissionReq params) {
        if (rolesService.getById(params.getRoleId()) == null) {
            return Result.err(ResponseStatus.ROLE_NOT_EXIST);
        }
        if (!rolePermissionsService.updPermission(params)) {
            return Result.err(ResponseStatus.ROLE_PERMISSION_UPD_ERROR);
        }
        return Result.ok(ResponseStatus.SUCCESS);
    }

    @Override
    public Result<Void> updMenu(UpdRoleMenuReq params) {
        if (rolesService.getById(params.getRoleId()) == null) {
            return Result.err(ResponseStatus.ROLE_NOT_EXIST);
        }
        if (!roleMenusService.updMenu(params)) {
            return Result.err(ResponseStatus.ROLE_PERMISSION_UPD_ERROR);
        }
        return Result.ok(ResponseStatus.SUCCESS);
    }

    @Override
    public Result<List<Integer>> getPermissionIds(Integer roleId) {
        List<Integer> perissionIds = rolePermissionsService.listByRoleId(roleId).stream().map(RolePermissions::getPermissionId).toList();
        return Result.ok(ResponseStatus.SUCCESS, perissionIds);
    }

    @Override
    public Result<List<Integer>> getMenuIds(Integer roleId) {
        List<Integer> menuIds = roleMenusService.listByRoleId(roleId).stream().map(RoleMenus::getMenuId).toList();
        return Result.ok(ResponseStatus.SUCCESS, menuIds);

    }
}
