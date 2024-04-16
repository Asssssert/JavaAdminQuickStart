package com.elay.adminquickstart.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.entity.Roles;
import com.elay.adminquickstart.request.auth.LoginReq;
import com.elay.adminquickstart.request.auth.RegisterReq;
import com.elay.adminquickstart.request.role.AddRoleReq;
import com.elay.adminquickstart.request.role.UpdRoleReq;
import com.elay.adminquickstart.response.Result;
import jakarta.servlet.http.HttpServletResponse;
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
    Result<Void> del(@PathVariable("roleId") @NotNull(message = "角色ID不能为空") Integer userId);

    @GetMapping("/{roleId}")
    Result<Roles> get(@PathVariable("roleId") @NotNull(message = "角色ID不能为空") Integer userId);

    @GetMapping("/page")
    Result<Page> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);

    @GetMapping("/list")
    Result<List<Roles>> list();

}
