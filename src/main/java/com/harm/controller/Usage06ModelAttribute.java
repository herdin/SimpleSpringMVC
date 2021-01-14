package com.harm.controller;

import com.harm.vo.Event;
import com.harm.vo.User;
import com.harm.vo.ValidationGroupForNotBlack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/usage06")
public class Usage06ModelAttribute {
    private Logger logger = LoggerFactory.getLogger(Usage06ModelAttribute.class);

    @RequestMapping(value = "/hello1/events")
    public String hello1(Model model) {
        Event event = new Event(1, "id-123", 10);
        model.addAttribute("myEvent", event);
        return this.getClass().getSimpleName();
    }

    @RequestMapping(value = "/hello2/events")
    @ResponseBody
    public String hello2(@ModelAttribute Event event, BindingResult bindingResult) {
        logger.debug("event -> {}", event);
        logger.debug("has errors?  -> {}", bindingResult.hasErrors());
        bindingResult.getAllErrors().forEach( c -> logger.debug("{}", c.toString()));
        return event.toString() ;
    }

    @RequestMapping(value = "/hello3/events")
    @ResponseBody
    public String hello3(@Valid @ModelAttribute Event event, BindingResult bindingResult) {
        logger.debug("event -> {}", event);
        logger.debug("has errors?  -> {}", bindingResult.hasErrors());
        bindingResult.getAllErrors().forEach( c -> logger.debug("{}", c.toString()));
        return event.toString() ;
    }

    @RequestMapping(value = "/hello4/events")
    @ResponseBody
    public String hello4(@Validated(ValidationGroupForNotBlack.class) @ModelAttribute Event event, BindingResult bindingResult) {
        logger.debug("event -> {}", event);
        logger.debug("has errors?  -> {}", bindingResult.hasErrors());
        bindingResult.getAllErrors().forEach( c -> logger.debug("{}", c.toString()));
        return event.toString() ;
    }

}
