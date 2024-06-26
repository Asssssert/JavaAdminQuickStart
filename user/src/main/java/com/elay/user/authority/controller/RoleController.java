package com.elay.user.authority.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.user.authority.response.Result;
import com.elay.user.authority.entity.Roles;
import com.elay.user.authority.request.role.AddRoleReq;
import com.elay.user.authority.request.role.UpdRoleMenuReq;
import com.elay.user.authority.request.role.UpdRolePermissionReq;
import com.elay.user.authority.request.role.UpdRoleReq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author LI
 * @since 2024/4/13
 */
@Validated
@RequestMapping("/role")
public interface RoleController {

    //基本角色接口
    @PostMapping("/add")
    Result<Void> add(@RequestBody @Valid AddRoleReq params);

    @PostMapping("/upd")
    Result<Void> upd(@RequestBody @Valid UpdRoleReq params);

    @DeleteMapping("/{roleId}")
    Result<Void> del(@PathVariable("roleId") @NotNull(message = "角色ID不能为空") Integer roleId);

    @GetMapping("/{roleId}")
    Result<Roles> get(@PathVariable("roleId") @NotNull(message = "角色ID不能为空") Integer roleId);

    @GetMapping("/page")
    Result<Page> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);

    @GetMapping("/list")
    Result<List<Roles>> list();

    @PostMapping("/upd/permission")
    Result<Void> updPermission(@RequestBody @Valid UpdRolePermissionReq params);

    @PostMapping("/upd/menu")
    Result<Void> updMenu(@RequestBody @Valid UpdRoleMenuReq params);

    @GetMapping("/permission/{roleId}")
    Result<List<Integer>> getPermissionIds(@PathVariable("roleId") @NotNull(message = "角色ID不能为空") Integer roleId);

    @GetMapping("/menu/{roleId}")
    Result<List<Integer>> getMenuIds(@PathVariable("roleId") @NotNull(message = "角色ID不能为空") Integer roleId);

}
