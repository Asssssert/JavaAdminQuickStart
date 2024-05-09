package com.elay.adminquickstart.authority.controller.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.elay.adminquickstart.authority.controller.AuthController;
import com.elay.adminquickstart.authority.response.Result;
import com.elay.adminquickstart.authority.service.impl.UsersService;
import com.elay.adminquickstart.emus.ResponseStatus;
import com.elay.adminquickstart.authority.request.auth.LoginReq;
import com.elay.adminquickstart.authority.request.auth.RegisterReq;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author LI
 * @since 2024/4/13
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // 允许所有来源的跨域请求，缓存时间为1小时
public class AuthControllerImpl implements AuthController {
    @Resource
    private UsersService usersService;

    @Override
    public Result<Void> login(@RequestBody @Valid LoginReq params) {
        if (usersService.login(params)) {
            return Result.ok(ResponseStatus.LOGIN_SUCCESS);
        }
        return Result.err(ResponseStatus.LOGIN_FAIL);
    }

    @Override
    public Result<Void> logout() {
        return Result.ok(ResponseStatus.LOGOUT_SUCCESS);
    }

    @Override
    public Result<Void> register(@RequestBody @Valid RegisterReq params) {
        if (usersService.register(params)) {
            return Result.ok(ResponseStatus.REGISTER_SUCCESS);
        }
        return Result.err(ResponseStatus.USERNAME_PASSWORD_ERROR);
    }

    @Override
    public void captcha(HttpServletResponse response) {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        //输出code
        System.out.println(lineCaptcha.getCode());
        //验证图形验证码的有效性，返回boolean值
//        lineCaptcha.verify("1234");
        try {
            lineCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
