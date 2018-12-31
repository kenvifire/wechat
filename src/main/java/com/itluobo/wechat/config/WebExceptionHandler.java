package com.itluobo.wechat.config;

import com.itluobo.wechat.mvc.entity.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public void generalExceptionHandler() {

    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void accessDeniedExceptionHandler() {

    }

}
