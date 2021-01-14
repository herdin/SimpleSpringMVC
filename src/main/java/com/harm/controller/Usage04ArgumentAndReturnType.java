package com.harm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/usage04")
public class Usage04ArgumentAndReturnType {
    private Logger logger = LoggerFactory.getLogger(Usage04ArgumentAndReturnType.class);

    @RequestMapping(value = "/hello1", headers = HttpHeaders.CACHE_CONTROL)
    @ResponseBody
    public String hello1(WebRequest webRequest) {
        logger.debug("webRequest.getContextPath() -> {}", webRequest.getContextPath());
        logger.debug("webRequest.getHeader(HttpHeaders.CACHE_CONTROL) -> {}", webRequest.getHeader(HttpHeaders.CACHE_CONTROL));
        logger.debug("webRequest.getLocale() -> {}", webRequest.getLocale());
        logger.debug("webRequest.getSessionId() -> {}", webRequest.getSessionId());
//        logger.debug("{}", webRequest.getUserPrincipal().getName());

        return "hello1";
    }

    @RequestMapping(value = "/hello2")
    @ResponseBody
    public String hello2(NativeWebRequest nativeWebRequest) {
        logger.debug("((HttpServletRequest)nativeWebRequest.getNativeRequest()).getAttribute(\"name\") -> {}", ((HttpServletRequest)nativeWebRequest.getNativeRequest()).getParameter("name"));
        return "hello2";
    }

}
