package com.mieker.santaApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GiftExceptionHandler {

    @ExceptionHandler(GiftException.class)
    public ResponseEntity<ErrorInfo> handleException(GiftException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (GiftError.GIFT_NOT_EXIST.equals(e.getGiftError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getGiftError().getMessage()));
    }
}
