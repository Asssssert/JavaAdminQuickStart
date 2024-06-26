package com.elay.user.security.handler;

import cn.hutool.json.JSONUtil;
import com.elay.user.authority.response.Result;
import com.elay.user.emus.ResponseStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class IAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("认证失败-ErrMsg:[{}]",accessDeniedException.getMessage());
        Result<Void> result = Result.err(ResponseStatus.UNAUTHORIZED);
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String str = JSONUtil.toJsonStr(result);
        response.getWriter().print(str);
    }
}
