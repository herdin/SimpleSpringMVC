package com.harm.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/usage03")
public class Usage03HeaderAndParameterMapping {
    @RequestMapping(value = "/hello1", headers = HttpHeaders.CACHE_CONTROL)
    @ResponseBody
    public String hello1() {
        return "hello1";
    }

    @RequestMapping(value = "/hello2", headers = "!" + HttpHeaders.FROM)
    @ResponseBody
    public String hello2() { return "hello2"; }

    @RequestMapping(value = "/hello3", headers = HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS + "=akey")
    @ResponseBody
    public String hello3() { return "hello3"; }

    @RequestMapping(value = "/hello4", params = "name")
    @ResponseBody
    public String hello4() { return "hello4"; }

    @RequestMapping(value = "/hello5", params = "!name")
    @ResponseBody
    public String hello5() { return "hello5"; }

    @RequestMapping(value = "/hello6", params = "name=spring")
    @ResponseBody
    public String hello6() { return "hello6"; }

    @RequestMapping(value = "/hello7", params = "name!=spring")
    @ResponseBody
    public String hello7() { return "hello7"; }
}
