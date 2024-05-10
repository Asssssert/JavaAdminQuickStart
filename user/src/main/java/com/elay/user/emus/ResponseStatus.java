package com.elay.user.emus;

import lombok.Getter;

/**
 * @author LI
 * @since 2024/4/13
 */
@Getter
public enum ResponseStatus {
    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    LOGIN_SUCCESS(200, "登录成功"),
    LOGIN_FAIL(401, "用户名或密码错误"),
    USER_STATE_FAIL(401, "用户状态异常"),
    LOGOUT_SUCCESS(200, "退出成功"),
    CAPTCHA_EXPIRED(401, "验证码过期"),
    LOGOUT_FAIL(401, "退出失败"),
    CAPTCHA_ERROR(401, "验证码错误"),
    CAPTCHA_NULL(401, "验证码为空"),
    NOT_FOUND(404, "未找到"),
    UNAUTHORIZED(401, "未授权"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),
    REQUEST_TIMEOUT(408, "请求超时"),
    CONFLICT(409, "冲突"),
    FORBIDDEN(403, "认证失败"),
    NOT_ACCEPTABLE(406, "不可接受"),
    //参数校验失败
    PARAMETER_ERROR(400, "参数校验失败"),
    ERROR(500, "错误"),
    REGISTER_SUCCESS(200, "注册成功"),
    REGISTER_FAIL(401, "注册失败"),
    USERNAME_PASSWORD_ERROR(401, "用户名或邮箱已存在"),
    USER_NOT_FOUND(401, "用户不存在"),
    ROLE_EXIST(401, "身份已存在"),
    ROLE_NOT_EXIST(401, "身份不存在"),
    NOT_DATA(401, "没有数据"),
    PERMISSION_EXIST_ERROR(401, "权限已存在"),
    PERMISSION_NOT_EXIST_ERROR(401, "权限不存在"),
    MENU_EXIST_FOUND(401, "菜单已存在"),
    ROLE_PERMISSION_UPD_ERROR(401, "身份权限修改失败"),
    ROLE_MENU_UPD_ERROR(401, "身份菜单修改失败"),
    MENU_NOT_FOUND(401,"菜单不存在" );


    private final int code;
    private final String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
