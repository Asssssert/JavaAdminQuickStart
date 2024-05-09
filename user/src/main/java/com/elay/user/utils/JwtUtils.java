package com.elay.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

/**
 * @author LI
 * @since 2024/5/9
 */
@Data
@Component
@ConfigurationProperties("eley.jwt")
public class JwtUtils {
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("your-secret-key"); // 替换为您的密钥
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = Duration.ofMinutes(30).toMillis(); // 访问令牌有效期
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = Duration.ofDays(7).toMillis(); // 刷新令牌有效期

    /**
     * 生成访问令牌
     *
     * @param username 用户名
     * @return 生成的JWT Token字符串
     */
    public static String generateAccessToken(String username) {
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .sign(ALGORITHM);
    }

    /**
     * 生成刷新令牌
     *
     * @param username 用户名
     * @return 刷新Token字符串
     */
    public static String generateRefreshToken(String username) {
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .sign(ALGORITHM);
    }

    /**
     * 验证Token并获取用户名
     *
     * @param token 待验证的JWT Token字符串
     * @return 用户名，验证失败则抛出异常
     * @throws JWTVerificationException 验证失败异常
     */
    public static String verifyTokenAndGetUsername(String token) throws JWTVerificationException {
        return JWT.require(ALGORITHM)
                .build()
                .verify(token)
                .getClaim("username")
                .asString();
    }
}
