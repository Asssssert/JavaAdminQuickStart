package com.elay.user.authority.response;

import com.elay.user.emus.ResponseStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LI
 * @since 2024/4/13
 */
@Getter
@Setter
@ToString
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResponseStatus status) {
        this(status.getCode(), status.getMsg(), null);
    }

    public static <T> Result<T> ok(ResponseStatus status, T data) {
        return new Result<>(status.getCode(), status.getMsg(), data);
    }

    public static Result<Void> ok(ResponseStatus status) {
        return new Result<>(status);
    }

    public static Result<Void> err(ResponseStatus status) {
        return new Result<>(status);
    }

    public static <T> Result<T> err(ResponseStatus status, T data) {
        return new Result<>(status.getCode(), status.getMsg(), data);
    }

}
