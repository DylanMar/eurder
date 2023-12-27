package com.switchfully.eurder.controller;

import com.switchfully.eurder.exception.AuthorizationException;
import com.switchfully.eurder.exception.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected void illegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    protected void illegalAuthorizationError(AuthorizationException e, HttpServletResponse response) throws IOException {
        response.sendError(FORBIDDEN.value(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    protected void illegalUserException(NotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    protected void exception(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), e.getMessage());
    }

}