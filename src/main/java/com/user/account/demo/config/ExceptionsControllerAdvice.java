package com.user.account.demo.config;

import com.user.account.demo.controller.ResourceNotFoundException;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<Object> resourceNotFoundException(RuntimeException rx, WebRequest request) {
        return getObjectResponseEntity(rx, request, HttpStatus.NOT_FOUND, "Resource not found for id");
    }

    private ResponseEntity<Object> getObjectResponseEntity(RuntimeException rx, WebRequest request, HttpStatus httpStatus, String errorMessage) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setStatus(httpStatus);
        errorDetail.setDebugMessage(rx.getMessage());
        errorDetail.setMessage(errorMessage);
        errorDetail.setTimestamp(LocalDateTime.now());
        return new ResponseEntity(errorDetail, new HttpHeaders(), httpStatus);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> anyOtherException(RuntimeException rx, WebRequest request) {
        return getObjectResponseEntity(rx, request, HttpStatus.INTERNAL_SERVER_ERROR, "Exception while processing error. Please contract administrator at phone number: 1234567890");
    }
}
