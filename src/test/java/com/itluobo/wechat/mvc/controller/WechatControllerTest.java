package com.itluobo.wechat.mvc.controller;

import com.itluobo.wechat.domain.service.WechatService;
import com.itluobo.wechat.mvc.filter.WechatRequestFilter;
import com.itluobo.wechat.uitls.AuthUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@WebMvcTest(WechatController.class)
public class WechatControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private WechatService wechatService;

    @Test
    public void testPing() throws Exception {
        mvc.perform(signRequest(get("/wechat/ping"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("pong"));
    }

    @Test
    public void testEchoMessage() throws Exception {
        final String randomStr = UUID.randomUUID().toString();

        mvc.perform(signRequest(get("/wechat/service/message"))
                .contentType(MediaType.APPLICATION_JSON)
                .param("echostr",randomStr))
                .andExpect(MockMvcResultMatchers.content().string(randomStr))
                .andDo(print());
    }

    @Test
    public void testTextMessage() throws Exception {
        mvc.perform(signRequest(get("/wechat/service/message"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private MockHttpServletRequestBuilder signRequest(final MockHttpServletRequestBuilder builder) {
       final String timeStamp = String.valueOf(Instant.now().toEpochMilli());
       final String nonce = String.valueOf(new Random().nextInt(1000));

       return signRequest(builder, timeStamp, WechatRequestFilter.TOKEN, nonce);
    }


    private MockHttpServletRequestBuilder signRequest(final MockHttpServletRequestBuilder builder,
                                                      final String timestamp, final String token, final String nonce) {
        return builder.param(WechatRequestFilter.TIME_STAMAP_KEY, timestamp)
                .param(WechatRequestFilter.NONCE_KEY, nonce)
                .param(WechatRequestFilter.SIGNATURE_KEY, AuthUtils.sign(timestamp, token, nonce));

    }

}
