package com.itluobo.wechat.mvc.filter;

import com.itluobo.wechat.mvc.entity.AccessDeniedException;
import com.itluobo.wechat.uitls.AuthUtils;
import org.springframework.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by kenvizhu on 15/6/5.
 */
public class WechatRequestFilter implements Filter{

    public static final String TOKEN = "itluobotoken";
    public static final String TIME_STAMAP_KEY = "timestamp";
    public static final String SIGNATURE_KEY = "signature";
    public static final String NONCE_KEY = "nonce";
    public static final Logger logger = LogManager.getLogger(WechatRequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)  servletRequest;

        String timestamp = request.getParameter(TIME_STAMAP_KEY);
        String signature = request.getParameter(SIGNATURE_KEY);
        String nonce = request.getParameter("nonce");

        logger.info(String.format("request with params[timestamp:%s,signature:%s,nonce:%s]",timestamp,
                signature,nonce));

        if(StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(signature)
                ||StringUtils.isEmpty(nonce)){
            throw new AccessDeniedException("invalid auth param");
        }

        String signResult = AuthUtils.sign(timestamp, nonce, TOKEN);

        if (!signature.equals(signResult)) {
            throw new AccessDeniedException(String.format("Invalid request,signature:%s, result:%s", signature, signResult));
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }


    @Override
    public void destroy() {

    }
}
