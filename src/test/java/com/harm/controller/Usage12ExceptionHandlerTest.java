package com.harm.controller;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Usage12ExceptionHandlerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void test() {
        Assert.assertThat("12345", Matchers.containsString("34"));
    }

    @Test
    public void hello1_1() throws Exception {
        mockMvc.perform(post("/usage12/hello1")
                .param("id", "1")
                .param("name", "foobar")
                .param("limit", "10")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("event exception")))
        ;
    }

    @Test
    public void hello1_2() throws Exception {
        mockMvc.perform(post("/usage12/hello1")
                .param("id", "2")
                .param("name", "foobar")
                .param("limit", "10")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("runtime exception")))
        ;
    }

    @Test
    public void hello1_3() throws Exception {
        mockMvc.perform(post("/usage12/hello1")
                .param("id", "99")
                .param("name", "foobar")
                .param("limit", "10")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("illegal argument exception")))
        ;
    }
}