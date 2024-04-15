package com.elay.adminquickstart.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.entity.Menu;
import com.elay.adminquickstart.request.menu.UpdMenuReq;
import com.elay.adminquickstart.request.menu.AddMenuReq;
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
@RequestMapping("/menu")
public interface MenuController {

    //基本角色接口
    @PostMapping("/add")
    Result<Void> add(@RequestBody @Valid AddMenuReq params);

    @PostMapping("/upd")
    Result<Void> upd(@RequestBody @Valid UpdMenuReq params);

    @DeleteMapping("/{roleId}")
    Result<Void> del(@PathVariable("roleId") @NotNull(message = "菜单ID不能为空") Integer userId);

    @GetMapping("/{roleId}")
    Result<Menu> get(@PathVariable("roleId") @NotNull(message = "菜单ID不能为空") Integer userId);

    @GetMapping("/page")
    Result<Page<Menu>> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);

    @GetMapping("/page")
    Result<Page<Menu>> pageByParentId(@RequestParam(value = "page", defaultValue = "0") Integer parentId, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);

}
