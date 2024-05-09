package com.elay.user.authority.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.user.authority.response.Result;
import com.elay.user.authority.response.user.AdminUserResp;
import com.elay.user.authority.request.user.AdminUpdUserReq;
import com.elay.user.authority.request.user.UpdUserRoleReq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LI
 * @since 2024/4/14
 */
@Validated
@RequestMapping("/user")
public interface UserController<T> {

    /**
     * 更新用户信息
     *
     * @param params
     * @return
     */
    @PutMapping("/{userId}")
    Result<Void> upd(@PathVariable("userId") @NotNull(message = "用户ID不能为空") Integer userId , @RequestBody @Valid AdminUpdUserReq params);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    Result<Void> del(@PathVariable("userId") @NotNull(message = "用户ID不能为空") Integer userId);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    Result<AdminUserResp> get(@PathVariable("userId") @NotNull(message = "用户ID不能为空") Integer userId);

    /**
     * 搜索用
     *
     * @param key
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/search")
    Result<Page> searchByKey(@RequestParam(value = "key", defaultValue = "") String key, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);

    @PostMapping("/upd/role")
    Result<Void> updRoleIds(@RequestBody @Valid UpdUserRoleReq params);

    @GetMapping("/role/{userId}")
    Result<List<Integer>> getUserIds(@PathVariable("userId") @NotNull(message = "用户ID不能为空") Integer userId);
}
