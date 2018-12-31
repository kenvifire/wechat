package com.itluobo.wechat.mvc.controller;

import com.itluobo.wechat.domain.service.WechatService;
import com.itluobo.wechat.mvc.entity.WechatResponse;
import com.itluobo.wechat.uitls.IOUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/wechat")
public class WechatController {

    private static final Logger logger = LogManager.getLogger(WechatController.class);


    @Autowired
    private WechatService wechatService;


    @RequestMapping("/service/message")
    public String printWelcome(final HttpServletRequest request, final HttpServletResponse resp) {
        String echoStr = request.getParameter("echostr");

        if (!StringUtils.isEmpty(echoStr)) {
            resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
            return echoStr;
        } else {
            resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
            final String message = IOUtils.readMessage(request);
            return message;
        }

    }

    @RequestMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
    public String ping() {
        return "pong";
    }

}