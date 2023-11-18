package com.jcastillo.warehouse.controller.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IllegalOperationException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;

}
