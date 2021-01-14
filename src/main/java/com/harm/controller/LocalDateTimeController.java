package com.harm.controller;

import com.harm.vo.Composite;
import com.harm.vo.CompositeWithDateTimeFormat;
import com.harm.vo.CompositeWithJsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDateTime;

@Controller
public class LocalDateTimeController {
    private Logger logger = LoggerFactory.getLogger(LocalDateTimeController.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Composite> exceptionHandler() {
        return ResponseEntity.badRequest().body(new Composite(99, "error", LocalDateTime.now(), LocalDateTime.now()));
    }

    @RequestMapping("/model-attribute")
    public ResponseEntity<Composite> test01(@ModelAttribute Composite composite) {
        logger.debug("recv data -> {}", composite);
        return ResponseEntity.ok().body(composite);
    }

    @RequestMapping("/model-attribute/date-time-format")
    @ResponseBody
    public ResponseEntity<CompositeWithDateTimeFormat> test02(CompositeWithDateTimeFormat composite) {
        logger.debug("recv data -> {}", composite);
        return ResponseEntity.ok().body(composite);
    }

    @RequestMapping("/request-body")
    public ResponseEntity<Composite> test03(@RequestBody Composite composite) {
        logger.debug("recv data -> {}", composite);
        return ResponseEntity.ok().body(composite);
    }

    @RequestMapping("/request-body/date-time-format")
    public ResponseEntity<CompositeWithDateTimeFormat> test04(@RequestBody CompositeWithDateTimeFormat composite) {
        logger.debug("recv data -> {}", composite);
        return ResponseEntity.ok().body(composite);
    }

    @RequestMapping("/request-body/json-format")
    public ResponseEntity<CompositeWithJsonFormat> test05(@RequestBody CompositeWithJsonFormat composite) {
        logger.debug("recv data -> {}", composite);
        return ResponseEntity.ok().body(composite);
    }
}
