package com.harm.controller;

import com.harm.exception.EventException;
import com.harm.exception.OtherEventException;
import com.harm.vo.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Usage12ExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(Usage12ExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<Event> errorHandler(OtherEventException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return ResponseEntity.ok().body(new Event(9, "99", 999));
    }

    @ExceptionHandler({RuntimeException.class, IllegalArgumentException.class})
    public String errorHandler(Exception exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "error";
    }

    @ExceptionHandler
    public String errorHandler(EventException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "error";
    }

    @PostMapping("/usage12/hello1")
    public String hello1(@ModelAttribute Event event) {
        logger.debug("recv event -> {}", event);
        switch(event.getId()) {
            case 1:
                throw new EventException("hello, this is exception for event exception handler.");
            case 2:
                throw new RuntimeException("hello, this is exception for runtime exception handler.");
            case 3:
                throw new OtherEventException("hello, this is exception for other event exception handler.");
            default:
                throw new IllegalArgumentException("hello, this is exception for illegal argument exception handler.");
        }
    }


}
