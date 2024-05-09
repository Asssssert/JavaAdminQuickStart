package com.elay.user.authority.controller.impl;

import com.elay.infra.constant.JwtConstants;
import com.elay.infra.constant.RedisConstants;
import com.elay.user.redis.RedisService;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.bean.BeanUtil;
import com.elay.user.authority.controller.AuthController;
import com.elay.user.authority.entity.Users;
import com.elay.user.authority.request.auth.LoginReq;
import com.elay.user.authority.request.auth.RegisterReq;
import com.elay.user.authority.response.Result;
import com.elay.user.authority.response.user.LoginResp;
import com.elay.user.authority.response.user.UserLoginResp;
import com.elay.user.authority.service.impl.UsersService;
import com.elay.user.emus.ResponseStatus;
import com.elay.user.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RedisService redisService;

    @Override
    public Result<LoginResp> login(@RequestBody @Valid LoginReq params) {
        //todo:验证码校验
        Users login = usersService.login(params);
        if (login != null) {
            //查看token是否已存在,已存在删除
            if (redisService.hasKey(RedisConstants.TOKEN_PREFIX + login.getUsername())) {
                redisService.del(RedisConstants.TOKEN_PREFIX + login.getUsername());
                redisService.del(RedisConstants.REFRESH_TOKEN_PREFIX + login.getUsername());
            }
            String token = JwtUtils.generateAccessToken(login.getUsername());
            String refreshToken = JwtUtils.generateRefreshToken(login.getUsername());
            UserLoginResp userLoginResp = new UserLoginResp();
            BeanUtil.copyProperties(login, userLoginResp);
            LoginResp loginResp = new LoginResp(userLoginResp, token, refreshToken);
            //添加token到redis
            redisService.set(RedisConstants.TOKEN_PREFIX + login.getUsername(), token, JwtConstants.TOKEN_EXPIRE_TIME);
            redisService.set(RedisConstants.REFRESH_TOKEN_PREFIX + login.getUsername(), refreshToken, JwtConstants.REF_TOKEN_EXPIRE_TIME);
            return Result.ok(ResponseStatus.LOGIN_SUCCESS, loginResp);
        }
        return Result.err(ResponseStatus.LOGIN_FAIL, null);
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
