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

/**
 * Created by hannahzhang on 15/6/5.
 */
public class WechatFilter implements Filter{

    private String token;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)  servletRequest;
        String timestamp = request.getParameter("timestamp");
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");


        List<String> params = Arrays.asList(timestamp,signature,nonce);
        Collections.sort(params);

        String paramStr = params.get(0) + params.get(1) + params.get(2);

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(paramStr.getBytes());
            String result =new String( messageDigest.digest());
            if(!token.equals(result)) {
                throw new RuntimeException("Invalid request");
            }

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }


    }

    @Override
    public void destroy() {

    }
}
