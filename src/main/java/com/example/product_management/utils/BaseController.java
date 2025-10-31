package com.example.product_management.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

public class BaseController<T> {
    private boolean success;
    private String message;
    private int code;
    private T data;

    public BaseController(boolean success, int code, String message, T data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static <T> ResponseEntity<BaseController<T>> success(String message, T data) {
        return new ResponseEntity<>(new BaseController<>(true, 200, message, data), null, HttpStatus.OK);
    }

    public static <T> ResponseEntity<BaseController<T>> success(String message, T data, int code) {
        return new ResponseEntity<>(new BaseController<>(true, code, message, data), null, HttpStatus.valueOf(code));
    }

    public static <T> ResponseEntity<BaseController<T>> fail(String message, T data) {
        return new ResponseEntity<>(new BaseController<>(false, 400, message, data), null, HttpStatus.valueOf(400));
    }

    public static <T> ResponseEntity<BaseController<T>> fail(String message, T data, int code) {
        return new ResponseEntity<>(new BaseController<>(false, code, message, data), null, HttpStatus.valueOf(code));
    }

    public static <T> ResponseEntity<BaseController<?>> internalError(String message, Object data) {
        return new ResponseEntity<>(new BaseController<>(false, 500, message, data), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
