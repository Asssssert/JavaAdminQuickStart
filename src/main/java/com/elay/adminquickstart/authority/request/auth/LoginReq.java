package com.elay.adminquickstart.authority.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author LI
 * @since 2024/4/13
 * 描述：
 * 登录请求
 */
@Data
public class LoginReq {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
