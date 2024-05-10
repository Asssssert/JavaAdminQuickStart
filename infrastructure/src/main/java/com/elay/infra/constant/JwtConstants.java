package com.elay.infra.constant;

/**
 * @author LI
 * @since 2024/5/9
 */
public class JwtConstants {
    //TOKEN过期时间单位秒1天
    public static final int TOKEN_EXPIRE_TIME = 24 * 60 * 60;
    //刷新TOKEN过期时间单位秒
    public static final int REF_TOKEN_EXPIRE_TIME = 2 * 24 * 60 * 60;

    public static final String JWT_SIGN_SECRET = "authority";
    public static final String JWT_HEADER = "Authorization";
    //用户权限过期时间
    public static final int JWT_EXPIRE_TIME = 24 * 60 * 60;


}
