package com.example.product_management.utils;

import com.example.product_management.exceptions.NoDataFoundException;
import com.example.product_management.exceptions.ValidateServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        BaseResponse<?> baseResponse = new BaseResponse<>(false, 500, "Error en el servidor" + ex.getMessage(), ex);
        return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidateServiceException.class)
    public ResponseEntity<?> validateService(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        BaseResponse<?> baseResponse = new BaseResponse<>(false, 500, ex.getMessage(), ex);
        return new ResponseEntity<>(baseResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noData(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        BaseResponse<?> baseResponse = new BaseResponse<>(false, 500, ex.getMessage(), ex);
        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
    }
}
