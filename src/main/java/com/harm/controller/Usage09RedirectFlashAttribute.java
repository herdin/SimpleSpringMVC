package com.harm.controller;

import com.harm.vo.Event;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

@Controller
@SessionAttributes({"initEvent"})
public class Usage09RedirectFlashAttribute {
    private Logger logger = LoggerFactory.getLogger(Usage09RedirectFlashAttribute.class);

    @GetMapping("/usage09/redirect/model/init")
    public String preinit(Model model) {
        Event initEvent = new Event();
        initEvent.setId(10);
        initEvent.setName("NEW EVENT 100");
        initEvent.setLimit(100);
        model.addAttribute("initEventName", initEvent.getName());
        model.addAttribute("initEventId", initEvent.getId());
        return "redirect:/usage09/redirect/next";
    }

    @GetMapping("/usage09/redirect/redirectattribute/init")
    public String init(Model model, RedirectAttributes redirectAttributes) {
        Event initEvent = new Event();
        initEvent.setId(11);
        initEvent.setName("NEW EVENT 111");
        initEvent.setLimit(111);
        redirectAttributes.addAttribute("initEventName", initEvent.getName());
        redirectAttributes.addAttribute("initEventId", initEvent.getId());
        return "redirect:/usage09/redirect/next";
    }

    @GetMapping("/usage09/redirect/next")
    @ResponseBody
    public String next(@RequestParam String initEventId, @RequestParam String initEventName) {
        logger.debug("id -> {}, name -> {}", initEventId, initEventName);
        return "hello!";
    }

    @GetMapping("/usage09/redirect/session/init")
    public String redirectAttributes1(Model model, RedirectAttributes redirectAttributes) {
        Event initEvent = new Event();
        initEvent.setId(11);
        initEvent.setName("NAME IN SESSION");
        initEvent.setLimit(1111);
        model.addAttribute("initEvent", initEvent);
        redirectAttributes.addAttribute("id", "22");
        redirectAttributes.addAttribute("name", "NAME IN REDIRECT");
        redirectAttributes.addAttribute("limit", "2222");
        return "redirect:/usage09/redirect/session/next";
    }

    @GetMapping("/usage09/redirect/session/next")
    @ResponseBody
    public String redirectAttributes2(@ModelAttribute Event event,
                       @RequestParam Map<String, String> paramMap,
                       @SessionAttribute Event initEvent,
                       HttpSession session) {
        logger.debug("model attribute event -> {}", event);
        logger.debug("session event -> {}", initEvent);
        logger.debug("session get -> {}", session.getAttribute("initEvent"));
        Iterator<String> iter = paramMap.keySet().iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            logger.debug("request parameter {} -> {}", key, paramMap.get(key));
        }
        return "hello!@";
    }

    //@SessionAttributes({"initEvent"}) 가 있는 상황에서 next2 에서 session attributes 와 같은 이름으로 redirect attribute 를 받으려고할 때 문제.
    //덮어 써진다.
    @GetMapping("/usage09/redirect/session/init2")
    public String redirectAttributes3(Model model, RedirectAttributes redirectAttributes) {
        Event initEvent = new Event();
        initEvent.setId(11);
        initEvent.setName("NAME IN SESSION");
        initEvent.setLimit(1111);
//        model.addAttribute("initEvent", initEvent);
        redirectAttributes.addAttribute("id", "22");
        redirectAttributes.addAttribute("name", "NAME IN REDIRECT");
        redirectAttributes.addAttribute("limit", "2222");
        return "redirect:/usage09/redirect/session/next2";
    }

    @GetMapping("/usage09/redirect/session/next2")
    @ResponseBody
    public String redirectAttributes4(@ModelAttribute("initEvent") Event event,
                       @RequestParam Map<String, String> paramMap,
                       @SessionAttribute Event initEvent,
                       HttpSession session) {
        logger.debug("model attribute event -> {}", event);
        logger.debug("session event -> {}", initEvent);
        logger.debug("session get -> {}", session.getAttribute("initEvent"));
        Iterator<String> iter = paramMap.keySet().iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            logger.debug("request parameter {} -> {}", key, paramMap.get(key));
        }
        return "hello!@";
    }

    @GetMapping("/usage09/flash/send")
    public String flashSend(RedirectAttributes redirectAttributes) {
        Event flashEvent = new Event();
        flashEvent.setId(123);
        flashEvent.setName("flash-event-name");
        flashEvent.setLimit(456);
        redirectAttributes.addFlashAttribute("flashEvent", flashEvent);
        return "redirect:/usage09/flash/recv";
    }

    @GetMapping("/usage09/flash/recv")
    @ResponseBody
    public String flashRecv(@ModelAttribute("flashEvent") Event event) {
        logger.debug("recv -> {}", event);
        return "flash!";
    }
}
