package com.itluobo.wechat.domain.service.impl;

import com.itluobo.wechat.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import com.itluobo.wechat.domain.entity.MsgType;
import com.itluobo.wechat.domain.entity.UserMessage;
import com.itluobo.wechat.domain.service.WechatService;
import org.springframework.stereotype.Service;

/**
 * Created by kenvizhu on 15/6/6.
 */
@Service("wechatService")
public class WechatServiceImpl implements WechatService{

    @Autowired
    private RobotService robotService;

    public UserMessage processMsg(UserMessage userMessage){

        UserMessage retMsg = new UserMessage();
        retMsg.setFromUserName(userMessage.getToUserName());
        retMsg.setToUserName(userMessage.getFromUserName());
        retMsg.setContent(robotService.getResponse(userMessage.getContent()));
        retMsg.setMsgType(MsgType.TEXT.getType());

        return retMsg;
    }
}
