package com.harm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harm.vo.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Usage11RequestBodyAndHttpEntityTest {

    private Logger logger = LoggerFactory.getLogger(Usage11RequestBodyAndHttpEntityTest.class);

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void hello1() throws Exception {
        Event event = new Event();
        event.setId(11);
        event.setName("new event");
        event.setLimit(500);
        String json = objectMapper.writeValueAsString(event);
        logger.debug("json string -> {}", json);

        mockMvc.perform(post("/usage11/hello1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void hello2() throws Exception {
        Event event = new Event();
        event.setId(22);
        event.setName("new event2");
        event.setLimit(660);
        String json = objectMapper.writeValueAsString(event);
        logger.debug("json string -> {}", json);

        mockMvc.perform(post("/usage11/hello2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }
}