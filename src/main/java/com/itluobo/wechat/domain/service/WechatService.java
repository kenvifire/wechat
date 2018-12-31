package com.itluobo.wechat.domain.service;

import com.itluobo.wechat.domain.entity.UserMessage;

/**
 * Created by kenvizhu on 15/6/6.
 */
public interface WechatService {

    UserMessage processMsg(UserMessage userMessage);


}
