package com.itluobo.wechat.uitls;

import com.itluobo.wechat.domain.entity.UserMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Created by kenvizhu on 15/6/11.
 */
public class MessageUtils {

    public static UserMessage parseUserMsg(String xml){

        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(UserMessage.class);

        UserMessage message = (UserMessage)xs.fromXML(xml);
        return message;
    }

    public static String convertMsg(UserMessage userMessage){
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(UserMessage.class);
        return xs.toXML(userMessage);
    }
}
