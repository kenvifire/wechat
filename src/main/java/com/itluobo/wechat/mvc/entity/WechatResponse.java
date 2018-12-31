package com.itluobo.wechat.mvc.entity;

public class WechatResponse {
    private String message;

    public WechatResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
