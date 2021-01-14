package com.harm.controller;

import com.harm.vo.Event;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Usage07SessionAttributeTest {
    private Logger logger = LoggerFactory.getLogger(Usage07SessionAttributeTest.class);
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void hello1() throws Exception {
        MvcResult result = mockMvc.perform(get("/usage07/hello1/events")
                .param("id", "2")
                .param("name", "spring")
                .param("limit", "-10")
        )
                .andDo(print())
                .andExpect(status().isOk())
            .andExpect(request().sessionAttribute("myEvent", CoreMatchers.notNullValue()))
        .andReturn()
        ;
        Event myEvent = (Event) result.getRequest().getSession().getAttribute("myEvent");
        logger.debug("event id -> {}", myEvent.getId());
    }
}