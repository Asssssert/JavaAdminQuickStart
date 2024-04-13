package com.elay.adminquickstart.controller.impl;

import com.elay.adminquickstart.controller.AuthController;
import com.elay.adminquickstart.emus.ResponseStatus;
import com.elay.adminquickstart.request.LoginReq;
import com.elay.adminquickstart.request.RegisterReq;
import com.elay.adminquickstart.response.Result;
import com.elay.adminquickstart.service.impl.UsersService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
