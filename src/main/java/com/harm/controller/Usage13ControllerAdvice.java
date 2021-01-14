package com.harm.controller;

import com.harm.exception.EventException;
import com.harm.exception.OtherEventException;
import com.harm.vo.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@ControllerAdvice
public class Usage13ControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(Usage13ControllerAdvice.class);

    @ExceptionHandler
    public ResponseEntity<Event> errorHandler(OtherEventException exception, Model model) {
        return ResponseEntity.ok().body(new Event(9, exception.getMessage(), 999));
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

}
