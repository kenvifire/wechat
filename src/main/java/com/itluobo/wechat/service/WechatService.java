package com.itluobo.wechat.service;

import com.itluobo.wechat.MsgType;
import com.itluobo.wechat.domain.UserMessage;

/**
 * Created by hannahzhang on 15/6/6.
 */
public interface WechatService {

    public UserMessage processMsg(UserMessage userMessage);


}
