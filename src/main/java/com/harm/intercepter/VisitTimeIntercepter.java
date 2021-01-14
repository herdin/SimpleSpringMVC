package com.harm.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class VisitTimeIntercepter implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(VisitTimeIntercepter.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("intercept!");
        if(request.getSession().getAttribute("visitTime") == null) {
            request.getSession().setAttribute("visitTime", LocalDateTime.now());
            logger.debug("intercept! and put visitTime");
        } else {
            logger.debug("intercept! and visitTime is already set");
        }
        return true;
    }
}
