package com.itluobo.wechat.domain.entity;

/**
 * Created by kenvizhu on 15/6/11.
 */
public enum MsgType {

    TEXT("text");

    private String type;

    private MsgType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
