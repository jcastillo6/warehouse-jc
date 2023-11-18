package com.jcastillo.warehouse.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
    private final MessageSource messageSource;

    @Autowired
    public ExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(HttpServletRequest request, Exception ex,
                                                 Locale locale) {
        ex.printStackTrace(); // TODO: Should be kept only for development
        return new ResponseEntity<>("Error found", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
