package com.harm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Usage04ArgumentAndReturnTypeTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello1() throws Exception {
        mockMvc
                .perform(get("/usage04/hello1").header(HttpHeaders.CACHE_CONTROL, "hello"))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void hello2() throws Exception {
        mockMvc
                .perform(get("/usage04/hello2").param("name", "hello2"))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }
}