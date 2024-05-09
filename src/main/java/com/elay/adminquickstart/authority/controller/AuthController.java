package com.elay.adminquickstart.authority.controller;

import com.elay.adminquickstart.authority.response.Result;
import com.elay.adminquickstart.authority.request.auth.LoginReq;
import com.elay.adminquickstart.authority.request.auth.RegisterReq;
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
public interface AuthController {

    /**
     * 登录
     *
     * @param params
     * @return
     */
    @PostMapping("/login")
    Result<Void> login(@RequestBody @Valid LoginReq params);

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    Result<Void> logout();

    /**
     * 注册
     *
     * @param params
     * @return
     */
    @PostMapping("/register")
    Result<Void> register(@RequestBody @Valid RegisterReq params);

    /**
     * 获取验证码
     *
     * @param response
     */
    @GetMapping("/captcha")
    void captcha(HttpServletResponse response);

}
