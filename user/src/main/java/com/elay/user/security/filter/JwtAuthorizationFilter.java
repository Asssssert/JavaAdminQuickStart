package com.elay.user.security.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.elay.infra.constant.JwtConstants;
import com.elay.infra.constant.RedisConstants;
import com.elay.user.authority.entity.Users;
import com.elay.user.authority.response.Result;
import com.elay.user.emus.ResponseStatus;
import com.elay.user.redis.RedisService;
import com.elay.user.security.bean.IUserDetails;
import com.elay.user.utils.JwtUtils;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LI
 * @since 2024/5/9
 */
@Component
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Resource
    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //  从request中获取token
            String token = this.getTokenFromHttpServletRequest(request);
            //  直接放行,由系统Security判断是否具有访问权限
            if (StringUtil.isNullOrEmpty(token)) {
                log.info("请求地址:[{}]-请求方法:[{}]", request.getRequestURI(), request.getMethod());
                filterChain.doFilter(request, response);
                return;
            }
            String username = JwtUtils.verifyTokenAndGetUsername(token);
            log.info("请求地址:[{}]-请求方法:[{}]-用户名:[{}]", request.getRequestURI(), request.getMethod(), username);
            if (username == null) {
                filterChain.doFilter(request, response);
                return;
            }
            IUserDetails userDetails = (IUserDetails) redisService.get(RedisConstants.LOGIN_USER_PREFIX + username);
            if (userDetails == null) {
                filterChain.doFilter(request, response);
                return;
            }
            //如果redis中没有Token
            if (!redisService.hasKey(RedisConstants.TOKEN_PREFIX + userDetails.getUsername())) {
                filterChain.doFilter(request, response);
                return;
            }
            //如果redis不匹配
            String redisToken = (String) redisService.get(RedisConstants.TOKEN_PREFIX + userDetails.getUsername());
            if (!token.equals(redisToken)) {
                filterChain.doFilter(request, response);
                return;
            }
            //权限列表
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//            List<SimpleGrantedAuthority> permList = (List<SimpleGrantedAuthority>) redisService.get(RedisConstants.PERM_PREFIX + username);
//            if (permList == null) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//            List<SimpleGrantedAuthority> sgas = permList.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("ErrMsg:[{}]", e.getMessage());
            filterChain.doFilter(request, response);
//            throw new TokenExpiredException(e.getMessage());
        }
    }

    /**
     * @Description: 从http中获取token
     * @Param: [request]
     * @return: java.lang.String
     * @Author: starao
     * @Date: 2021/11/27
     */
    private String getTokenFromHttpServletRequest(HttpServletRequest request) {
        //  通过Authorization获取token
        String authorization = request.getHeader(JwtConstants.JWT_HEADER);
        if (StringUtils.isNotBlank(authorization)) {
            return authorization;
        }
        return null;
    }
}
