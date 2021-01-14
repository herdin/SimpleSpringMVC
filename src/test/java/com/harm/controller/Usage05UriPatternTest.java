package com.harm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Usage05UriPatternTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello1() throws Exception {
        mockMvc.perform(get("/usage05/hello1/id/243;name=herdin;nickname=hythloth;anagram=epubaal"))
                .andDo(print())
                .andExpect(jsonPath("id").value("243"))
        ;
    }

    @Test
    public void hello2() throws Exception {
        mockMvc.perform(post("/usage05/hello2").param("name", "epubaal2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello2-epubaal2"))
        ;

        mockMvc.perform(get("/usage05/hello2").param("age", "20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello2-DEFAULT_NAME"))
        ;
    }

    @Test
    public void hello3() throws Exception {
        mockMvc.perform(get("/usage05/hello3").param("name", "epubaal3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello3-epubaal3"))
        ;
    }

    @Test
    public void hello4() throws Exception {
        mockMvc.perform(get("/usage05/hello4").param("name", "epubaal4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello4-epubaal4"))
        ;
    }

    @Test
    public void hello5() throws Exception {
        mockMvc.perform(get("/usage05/hello5")
                .param("name", "epubaal5")
                .param("age", "20")
                .param("foo", "bar")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello5-epubaal5"))
        ;
    }
}