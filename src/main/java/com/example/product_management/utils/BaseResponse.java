package com.example.product_management.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;


    public static <T> ResponseEntity<BaseResponse<T>> success(String message, T data) {
        return new ResponseEntity<>(new BaseResponse<>(true, 200, message, data), HttpStatus.OK);
    }

    public static <T> ResponseEntity<BaseResponse<T>> success(String message, T data, int code) {
        return new ResponseEntity<>(new BaseResponse<>(true, code, message, data), HttpStatus.valueOf(code));
    }

    public static <T> ResponseEntity<BaseResponse<T>> fail(String message, T data) {
        return new ResponseEntity<>(new BaseResponse<>(true, 400, message, data), HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<BaseResponse<T>> fail(String message, T data, int code) {
        return new ResponseEntity<>( new BaseResponse<>(true, code, message, data), HttpStatus.valueOf(code));
    }


}
