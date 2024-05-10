package com.elay.infra.constant;

public class RedisConstants {
    //过期时间单位秒1天
    public static final int REDIS_EXPIRE_TIME = 24 * 60 * 60;
    public static final String TOKEN_PREFIX = "TOKEN_";
    public static final String REFRESH_TOKEN_PREFIX = "TOKEN_REFRESH_";
    public static final String PERM_PREFIX = "PERM_";
    public static final String LOGIN_USER_PREFIX = "LOGIN_USER_";
}
