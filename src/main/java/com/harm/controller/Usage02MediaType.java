package com.harm.controller;

import com.harm.annotaion.Hello3GetMediaTypeJsonMapping;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/usage02")
public class Usage02MediaType {
    @RequestMapping(value = "/hello1", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String hello1() {
        return "hello1";
    }

    @RequestMapping(value = "/hello2", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String hello2() {
        return "hello2";
    }

    @Hello3GetMediaTypeJsonMapping
//    @RequestMapping(method = RequestMethod.GET, value = "/hello3", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
    public String hello3() { return "hello3"; }
}
