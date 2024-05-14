package com.elay.user.authority.controller;

import com.elay.user.authority.response.Result;
import com.elay.user.authority.request.auth.LoginReq;
import com.elay.user.authority.request.auth.RegisterReq;
import com.elay.user.authority.response.user.LoginResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author LI
 * @since 2024/4/13
 */
@Validated
@RequestMapping("/auth")
@Tag(name = "认证接口")
public interface AuthController {

    /**
     * 登录
     *
     * @param params
     * @return
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    Result login(@RequestBody @Valid LoginReq params);

    @Operation(summary = "退出")
    @GetMapping("/logout")
    Result logout();

    @Operation(summary = "注册")
    @PostMapping("/register")
    Result register(@RequestBody @Valid RegisterReq params);

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    void captcha(HttpServletResponse response);

    @GetMapping("/test")
    Result test();

    @GetMapping("/test2")
    Result test2();

}
