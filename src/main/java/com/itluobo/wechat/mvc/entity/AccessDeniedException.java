package com.itluobo.wechat.mvc.entity;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String message) {
        super(message);
    }
}
