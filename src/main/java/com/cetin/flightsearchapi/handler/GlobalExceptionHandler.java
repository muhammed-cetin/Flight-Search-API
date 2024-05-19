package com.cetin.flightsearchapi.handler;

import com.cetin.flightsearchapi.handler.exceptions.ErrorCode;
import com.cetin.flightsearchapi.handler.exceptions.ErrorDetails;
import com.cetin.flightsearchapi.handler.exceptions.GlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(GlobalException ex) {
        ErrorCode errorCode = ex.getErrorCode(); // İstisna tarafından belirlenen hata kodu
        ErrorDetails errorDetails = new ErrorDetails(errorCode);
        return new ResponseEntity<>(errorDetails, errorCode.getHttpStatus());
    }

}
