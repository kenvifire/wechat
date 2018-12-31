package com.itluobo.wechat.config;

import com.itluobo.wechat.mvc.filter.ExceptionHandlerFilter;
import com.itluobo.wechat.mvc.filter.WechatRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<WechatRequestFilter> wechatRequestFilter() {
        FilterRegistrationBean<WechatRequestFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WechatRequestFilter());
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ExceptionHandlerFilter> exceptionHandlerFilter() {
        FilterRegistrationBean<ExceptionHandlerFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ExceptionHandlerFilter());
        return registrationBean;
    }



}
