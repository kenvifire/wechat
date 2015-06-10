package com.itluobo.wechat.filter;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by hannahzhang on 15/6/5.
 */
public class WechatFilter implements Filter{

    private String token = "itluobotoken";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)  servletRequest;
        String timestamp = request.getParameter("timestamp");
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");

        if(StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(signature)
                ||StringUtils.isEmpty(nonce)){
            throw new RuntimeException("invalid auth param");
        }

        List<String> params = Arrays.asList(timestamp,nonce,token);
        Collections.sort(params);

        String paramStr = params.get(0) + params.get(1) + params.get(2);

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(paramStr.getBytes());
            String result =byte2hex(messageDigest.digest());

            if(!signature.equals(result)) {
                throw new RuntimeException(String.format("Invalid request,signature:%s, result:%s",signature,result));
            }

            filterChain.doFilter(servletRequest,servletResponse);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }


    }

    public String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs;
    }

    @Override
    public void destroy() {

    }
}
