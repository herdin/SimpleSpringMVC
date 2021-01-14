package com.harm.controller;

import com.harm.exception.OtherEventException;
import com.harm.vo.Event;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/usage11")
public class Usage11RequestBodyAndHttpEntity {
    private Logger logger = LoggerFactory.getLogger(Usage11RequestBodyAndHttpEntity.class);

    @PostMapping("/hello1")
    public String hello1(@RequestBody Event event) {
        logger.debug("recv event -> {}", event);
        return "hello1";
    }

    @PostMapping("/hello2")
    public String hello2(HttpEntity<Event> httpEntity) {
        logger.debug("recv http entity -> {}", httpEntity);
        logger.debug("recv event -> {}", httpEntity.getBody());
        return "hello2";
    }

    //handler for usage 13 controller advice
    @GetMapping("/hello3")
    public String hello3() {
        if(true) {
            throw new OtherEventException("from usage11, hello3");
        }
        return "hello3";
    }
}
