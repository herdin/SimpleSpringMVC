package com.harm.controller;

import com.harm.vo.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/usage07")
@SessionAttributes({"myEvent"})
public class Usage07SessionAttributes {
    private Logger logger = LoggerFactory.getLogger(Usage07SessionAttributes.class);
    private static ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/events/form")
    public String form(Model model) {
        model.addAttribute("size", events.size());
        return "/events/id";
    }

    @PostMapping("/events/id")
    public String setId(@ModelAttribute Event event, Model model) {
        logger.debug("-> {}", event);
        Event newEvent = new Event();
        newEvent.setId(event.getId());
        model.addAttribute("myEvent", newEvent);
        return "redirect:/events/name";
    }
    @GetMapping("/events/name")
    public String gotoName() {
        return "/events/name";
    }

    @PostMapping("/events/name")
    public String setName(@ModelAttribute("myEvent") Event event, Model model) {
        logger.debug("-> {}", event);
        model.addAttribute("myEvent", event);
        return "redirect:/events/limit";
    }
    @GetMapping("/events/limit")
    public String gotoLimit() {
        return "/events/limit";
    }

    @PostMapping("/events/limit")
    public String setLimit(@ModelAttribute("myEvent") Event event, Model model, SessionStatus sessionStatus) {
        logger.debug("-> {}", event);
        events.add(event);
        sessionStatus.setComplete();
        return "redirect:/events/form";
    }




    @PostMapping("/events/form")
    public String createForm(@ModelAttribute Event event, Model model) {
        logger.debug("-> {}", event);
        events.add(event);
        return "redirect:/events/form";
    }


    @RequestMapping(value = "/hello1/events")
    @ResponseBody
    public String hello1(Event event, HttpSession httpSession, Model model) {
        logger.debug("-> {}", event);
//        httpSession.setAttribute("myEvent", event);
        model.addAttribute("myEvent", event);
        return "hello1";
    }
}
