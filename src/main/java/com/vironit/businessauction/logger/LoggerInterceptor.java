package com.vironit.businessauction.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String body = request.getReader().lines().collect(Collectors.joining());
        log.info("[method methods]" +"["+ request.getMethod() + "]" + " "
                + "[url of receiver][" + request.getRequestURL() + "]" + " "
                + "[url of sender]" + "["+new URL(request.getRequestURL().toString()).getHost()+"]" + " "
                + "[request body]" + "["+ body + "]" + " "
                + "[timestamp]" + "[" + LocalDateTime.now() +"]");

        return true;
    }

}
