package com.jcastillo.warehouse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcastillo.warehouse.controller.exception.IllegalOperationException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> handleException(IllegalOperationException e) {
        return new ResponseEntity<>(e.getErrorMessage(), e.getStatus());
    }
}
