package com.itluobo.wechat.service.impl;

import com.itluobo.wechat.MsgType;
import com.itluobo.wechat.domain.UserMessage;
import com.itluobo.wechat.service.WechatService;
import org.springframework.stereotype.Service;

/**
 * Created by hannahzhang on 15/6/6.
 */
@Service("wechatService")
public class WechatServiceImpl implements WechatService{

    public UserMessage processMsg(UserMessage userMessage){

        UserMessage retMsg = new UserMessage();
        retMsg.setFromUserName(userMessage.getToUserName());
        retMsg.setToUserName(userMessage.getFromUserName());
        retMsg.setContent("ฤ๚บรฃก");
        retMsg.setMsgType(MsgType.TEXT.getType());

        return retMsg;
    }
}
