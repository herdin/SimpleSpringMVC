package com.harm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Usage03HeaderAndParameterMappingTest implements SimpleTestSupport {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello1() throws Exception {
        mockMvc
                .perform(get("/usage03/hello1").header(HttpHeaders.FROM, "localhost"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc
            .perform(get("/usage03/hello1").header(HttpHeaders.CACHE_CONTROL, "hehe"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("hello1"));

        mockMvc
                .perform(options("/usage03/hello1"))
                .andDo(print())
                .andExpect(header().exists(HttpHeaders.ALLOW))
                .andExpect(header().stringValues(HttpHeaders.ALLOW, hasItems(containsString("GET"))))
        ;
        mockMvc
                .perform(head("/usage03/hello1").header(HttpHeaders.CACHE_CONTROL, "hehe"))
                .andDo(print())
                .andExpect(status().isOk())
        ;

    }

    @Test
    public void hello2() throws Exception {
        mockMvc
                .perform(get("/usage03/hello2").header(HttpHeaders.FROM, "hehe"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc
                .perform(get("/usage03/hello2").header(HttpHeaders.CACHE_CONTROL, "hehe"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello2"));
    }

    @Test
    public void hello3() throws Exception {
        mockMvc
                .perform(get("/usage03/hello3").header(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "bkey"))
                .andDo(print())
                .andExpect(status().isNotFound());
        mockMvc
                .perform(get("/usage03/hello3").header(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "akey"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello3"));
    }

    @Test
    public void hello4() throws Exception {
        mockMvc
                .perform(get("/usage03/hello4").param("age", "hello"))
                .andDo(print())
                .andExpect(status().isBadRequest());
        mockMvc
                .perform(get("/usage03/hello4").param("name", "hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello4"));
    }

    @Test
    public void hello5() throws Exception {
        mockMvc
                .perform(get("/usage03/hello5").param("name", "hello"))
                .andDo(print())
                .andExpect(status().isBadRequest());
        mockMvc
                .perform(get("/usage03/hello5").param("age", "hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello5"));
    }

    @Test
    public void hello6() throws Exception {
        mockMvc
                .perform(get("/usage03/hello6").param("name", "webflux"))
                .andDo(print())
                .andExpect(status().isBadRequest());
        mockMvc
                .perform(get("/usage03/hello6").param("name", "spring"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello6"));
    }

    @Test
    public void hello7() throws Exception {
        mockMvc
                .perform(get("/usage03/hello7").param("name", "spring"))
                .andDo(print())
                .andExpect(status().isBadRequest());
        mockMvc
                .perform(get("/usage03/hello7").param("name", "hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello7"));
    }
}