package com.harm.controller;

import com.harm.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/usage05")
public class Usage05UriPattern {
    private Logger logger = LoggerFactory.getLogger(Usage05UriPattern.class);

    @RequestMapping(value = "/hello1/id/{id}")
    @ResponseBody
    public User hello1(@PathVariable String id, @MatrixVariable String name, @MatrixVariable String nickname, @MatrixVariable String anagram) {
        logger.debug("id -> {}, name -> {}, nickname -> {}, anagram -> {}", id, name, nickname, anagram);
        User user = new User.Builder().id(id).name(name).nickname(nickname).anagram(anagram).build();
        return user;
    }

    @RequestMapping("/hello2")
    @ResponseBody
    public String hello2(@RequestParam(value = "name", required = false, defaultValue = "DEFAULT_NAME") String thisIsName) {
        logger.debug("thisIsName -> {}", thisIsName);
        return "hello2-" + thisIsName;
    }

    @RequestMapping("/hello3")
    @ResponseBody
    public String hello3(@RequestParam String name) {
        logger.debug("name -> {}", name);
        return "hello3-" + name;
    }

    @RequestMapping("/hello4")
    @ResponseBody
    public String hello4(String name) {
        logger.debug("name -> {}", name);
        return "hello4-" + name;
    }

    @RequestMapping("/hello5")
    @ResponseBody
    public String hello5(@RequestParam Map<String, String> params) {
        params.forEach((k, v) -> { logger.debug("k, v -> {}, {}", k, v);});
        logger.debug("name -> {}", params.get("name"));
        return "hello5-" + params.get("name");
    }


}
