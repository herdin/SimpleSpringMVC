package com.harm.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usage01")
public class Usage01UriMapping {

    @RequestMapping("/hello1")
    public String hello1() {
        return "hello1";
    }

    @RequestMapping("/hello2/???")
    public String hello2() {
        return "hello2";
    }

    @RequestMapping("/hello3/*")
    public String hello3() {
        return "hello3";
    }

    @RequestMapping("/hello4/**")
    public String hello4() {
        return "hello4";
    }

    @RequestMapping("/hello5/{name:[a-z]+}")
    public String hello5(@PathVariable String name) {
        return name;
    }

    @RequestMapping("/hello6")
    public String hello6() {
        return "hello6";
    }
}
