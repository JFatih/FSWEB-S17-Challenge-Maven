package com.workintech.spring17challenge.exceptions;

import com.workintech.spring17challenge.controller.CourseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ApiException apiException){
        log.error("ApiException occured! Exception details: " + apiException);
        ApiErrorResponse error = new ApiErrorResponse(apiException.getHttpStatus().value(), apiException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error,apiException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception){
        log.error("Unknown Exception occured! Exception details: " + exception);
        ApiErrorResponse error = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
