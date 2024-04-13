package com.elay.adminquickstart.controller;

import com.elay.adminquickstart.request.LoginReq;
import com.elay.adminquickstart.request.RegisterReq;
import com.elay.adminquickstart.response.Result;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.http.HttpResponse;


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

    // 返回验证码图片流
    @GetMapping("/captcha")
    void captcha(HttpServletResponse response);

}
