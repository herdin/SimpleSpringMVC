package com.harm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class Usage08SessionAttribute {
    private Logger logger = LoggerFactory.getLogger(Usage08SessionAttribute.class);

    @GetMapping("/usage08/visit")
    @ResponseBody
    public String visit(@SessionAttribute LocalDateTime visitTime) {
        //com.harm.intercepter.VisitTimeIntercepter 에서 넣어줌.
        logger.debug("visit time -> {}", visitTime);
        return visitTime.toString();
    }
}
