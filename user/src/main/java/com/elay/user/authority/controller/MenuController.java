package com.elay.user.authority.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.user.authority.response.Result;
import com.elay.user.authority.entity.Menu;
import com.elay.user.authority.request.menu.UpdMenuReq;
import com.elay.user.authority.request.menu.AddMenuReq;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "添加菜单")
    @PostMapping("/add")
    Result<Void> add(@RequestBody @Valid AddMenuReq params);

    @PostMapping("/upd")
    Result<Void> upd(@RequestBody @Valid UpdMenuReq params);

    @DeleteMapping("/{menuId}")
    Result<Void> del(@PathVariable("menuId") @NotNull(message = "菜单ID不能为空") Integer menuId);

    @GetMapping("/{menuId}")
    Result<Menu> get(@PathVariable("menuId") @NotNull(message = "菜单ID不能为空") Integer menuId);

    @GetMapping("/page")
    Result<Page> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);

    @GetMapping("/page/parentId")
    Result<Page<Menu>> pageByParentId(@RequestParam(value = "parentId", defaultValue = "0") Integer parentId, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);

}
