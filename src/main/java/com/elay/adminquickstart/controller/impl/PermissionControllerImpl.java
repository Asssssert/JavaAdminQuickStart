package com.elay.adminquickstart.controller.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.controller.PermissionController;
import com.elay.adminquickstart.emus.ResponseStatus;
import com.elay.adminquickstart.entity.Permissions;
import com.elay.adminquickstart.entity.Roles;
import com.elay.adminquickstart.request.permission.AddPermissionReq;
import com.elay.adminquickstart.request.permission.UpdPermissionReq;
import com.elay.adminquickstart.response.Result;
import com.elay.adminquickstart.service.impl.PermissionsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LI
 * @since 2024/4/15
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // 允许所有来源的跨域请求，缓存时间为1小时
public class PermissionControllerImpl implements PermissionController {
    @Resource
    private PermissionsService permissionsService;
    @Override
    public Result<Void> add(AddPermissionReq params) {
        if(!permissionsService.add(params)){
            return Result.err(ResponseStatus.PERMISSION_EXIST_ERROR);
        }
        return Result.ok(ResponseStatus.SUCCESS);

    }

    @Override
    public Result<Void> upd(UpdPermissionReq params) {
        if(!permissionsService.upd(params)){
            return Result.err(ResponseStatus.PERMISSION_NOT_EXIST_ERROR);
        }
        return Result.ok(ResponseStatus.SUCCESS);
    }

    @Override
    public Result<Void> del(Integer userId) {
        if(!permissionsService.removeById(userId)){
            return Result.err(ResponseStatus.PERMISSION_NOT_EXIST_ERROR);
        }
        return Result.ok(ResponseStatus.SUCCESS);
    }

    @Override
    public Result<Permissions> get(Integer userId) {
        Permissions byId = permissionsService.getById(userId);
        if(byId==null){
            return Result.err(ResponseStatus.PERMISSION_NOT_EXIST_ERROR,null);
        }
        return Result.ok(ResponseStatus.SUCCESS,byId);
    }

    @Override
    public Result<Page> page(Integer page, Integer size) {
        Page<Permissions> list = permissionsService.page(new Page<>(page, size));
        if(list!=null){
            return Result.ok(ResponseStatus.SUCCESS,list);
        }
        return Result.err(ResponseStatus.NOT_FOUND,null);
    }
}
