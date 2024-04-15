package com.elay.adminquickstart.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.entity.Permissions;
import com.elay.adminquickstart.entity.Roles;
import com.elay.adminquickstart.request.permission.UpdPermissionReq;
import com.elay.adminquickstart.request.permission.AddPermissionReq;
import com.elay.adminquickstart.response.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author LI
 * @since 2024/4/13
 */
@Validated
@RequestMapping("/permission")
public interface PermissionController {

    //基本角色接口
    @PostMapping("/add")
    Result<Void> add(@RequestBody @Valid AddPermissionReq params);

    @PostMapping("/upd")
    Result<Void> upd(@RequestBody @Valid UpdPermissionReq params);

    @DeleteMapping("/{roleId}")
    Result<Void> del(@PathVariable("roleId") @NotNull(message = "权限ID不能为空") Integer userId);

    @GetMapping("/{roleId}")
    Result<Permissions> get(@PathVariable("roleId") @NotNull(message = "权限ID不能为空") Integer userId);

    @GetMapping("/page")
    Result<Page> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);

}
