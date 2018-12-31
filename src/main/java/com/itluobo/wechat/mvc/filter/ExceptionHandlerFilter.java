package com.itluobo.wechat.mvc.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itluobo.wechat.mvc.entity.WechatResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter  {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {

            // custom error response class used across my project
            WechatResponse wechatResponse = new WechatResponse(e.getMessage());

            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().write(convertObjectToJson(wechatResponse));
        }
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
