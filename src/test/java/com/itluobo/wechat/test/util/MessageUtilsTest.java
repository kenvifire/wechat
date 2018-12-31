package com.itluobo.wechat.test.util;

import com.itluobo.wechat.domain.entity.UserMessage;
import com.itluobo.wechat.uitls.MessageUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by kenvizhu on 15/6/11.
 */
public class MessageUtilsTest {

    @Test
    public void testParseUserMsg(){
        UserMessage userMessage = MessageUtils.parseUserMsg("<xml><ToUserName><![CDATA[gh_4a7a6b856caf]]></ToUserName><FromUserName><![CDATA[opiipuN4WWniQlsYBgMo93LIVol4]]></FromUserName><CreateTime>1433981790</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[Getty]]></Content><MsgId>6158904891315404618</MsgId></xml>");

        Assert.assertEquals(userMessage.getToUserName(), "gh_4a7a6b856caf");
        Assert.assertEquals(userMessage.getFromUserName(), "opiipuN4WWniQlsYBgMo93LIVol4");
        Assert.assertEquals(userMessage.getCreateTime().longValue(), 1433981790);
        Assert.assertEquals(userMessage.getContent(), "Getty");
        Assert.assertEquals(userMessage.getMsgId(), "6158904891315404618");


    }
}
