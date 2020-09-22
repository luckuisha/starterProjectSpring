package com.example.demo.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZonedDateTime;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException e, HttpStatus httpStatus){
        Exception exception = new Exception(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, httpStatus);
    }
}

