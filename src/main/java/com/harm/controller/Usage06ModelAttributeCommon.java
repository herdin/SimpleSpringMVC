package com.harm.controller;

import com.harm.vo.Event;
import com.harm.vo.ValidationGroupForNotBlack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/usage06/common/{eventId}")
public class Usage06ModelAttributeCommon {
    private Logger logger = LoggerFactory.getLogger(Usage06ModelAttributeCommon.class);

    private Map<Integer, Event> eventRepository;

    public Usage06ModelAttributeCommon() {
        eventRepository = new HashMap<>();
        IntStream.rangeClosed(0, 1000).forEach(
                i -> eventRepository.put(i, new Event(i, "event-"+i, i*10))
        );
    }
    public void printRepository() {
        eventRepository.keySet().stream().forEach(i -> logger.debug("event -> {}", eventRepository.get(i)));
    }
    @ModelAttribute("event")
    public Event getEventFromRepository(@PathVariable("eventId") int eid) {
        logger.debug("common model attribute -> {}", eid);
        printRepository();
        return eventRepository.get(eid);
    }

    @RequestMapping("/hello5/events")
    @ResponseBody
    public String hello5(Event event) {
        logger.debug("event -> {}", event);
        return event.getName();
    }
}
