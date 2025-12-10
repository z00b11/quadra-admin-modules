package com.wuyunbin.quadra.admin.common;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private boolean success;
    private long timestamp;
    private T data;

    public static <T> Result<T> success() {
        Result<T> r = new Result<>();
        r.code = 0;
        r.message = "OK";
        r.success = true;
        r.timestamp = System.currentTimeMillis();
        return r;
    }

    public static <T> Result<T> success(T data) {
        Result<T> r = success();
        r.data = data;
        return r;
    }

    public static <T> Result<T> fail(String message) {
        Result<T> r = new Result<>();
        r.code = -1;
        r.message = message;
        r.success = false;
        r.timestamp = System.currentTimeMillis();
        return r;
    }

    public static <T> Result<T> fail(int code, String message) {
        Result<T> r = new Result<>();
        r.code = code;
        r.message = message;
        r.success = false;
        r.timestamp = System.currentTimeMillis();
        return r;
    }
}
