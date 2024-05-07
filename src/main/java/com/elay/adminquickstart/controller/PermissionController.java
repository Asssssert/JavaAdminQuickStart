package com.elay.adminquickstart.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.entity.Permissions;
import com.elay.adminquickstart.entity.Roles;
import com.elay.adminquickstart.request.permission.UpdPermissionReq;
import com.elay.adminquickstart.request.permission.AddPermissionReq;
import com.elay.adminquickstart.response.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;
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

    @DeleteMapping("/{permissionId}")
    Result<Void> del(@PathVariable("permissionId") @NotNull(message = "权限ID不能为空") Integer permissionId);

    @GetMapping("/{permissionId}")
    Result<Permissions> get(@PathVariable("permissionId") @NotNull(message = "权限ID不能为空") Integer permissionId);

    @GetMapping("/page")
    Result<Page> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);

    @GetMapping("/page/parentId")
    Result<Page> pageByParentId(@RequestParam(value = "parentId", defaultValue = "0") Integer parentId, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);

}
