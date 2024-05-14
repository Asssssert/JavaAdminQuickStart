package com.elay.user.handler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.elay.user.authority.response.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import com.elay.user.emus.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LI
 * @since 2024/4/13
 */
@ControllerAdvice
@Slf4j
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Map<String, String>> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("请求路径:[{}]，参数错误:[{}]", request.getRequestURI(), errors);
        return Result.err(ResponseStatus.PARAMETER_ERROR, errors);
    }

//    @ExceptionHandler(TokenExpiredException.class)
//    public Result handleTokenException(HttpServletRequest request, Exception ex) {
//        log.error("请求路径:[{}]，token异常:[{}]", request.getRequestURI(), ex.getMessage());
//        return Result.err(ResponseStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(Exception.class)
    public Result handleException(HttpServletRequest request, Exception ex) {
        log.error("请求路径:[{}]，异常:[{}]", request.getRequestURI(), ex.getMessage());
        return Result.err(ResponseStatus.UNAUTHORIZED);
    }
}
