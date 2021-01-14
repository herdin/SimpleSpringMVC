package com.harm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Usage06ModelAttributeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello1() throws Exception {
        mockMvc.perform(get("/usage06/hello1/events"))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void hello2() throws Exception {
        mockMvc.perform(get("/usage06/hello2/events")
                .param("id", "2")
                .param("name", "spring")
                .param("limit", "10")
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;

        mockMvc.perform(get("/usage06/hello2/events")
                .param("id", "asfd")
                .param("name", "spring")
                .param("limit", "10")
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void hello3() throws Exception {

        mockMvc.perform(get("/usage06/hello3/events")
                .param("id", "2")
                .param("name", "spring")
                .param("limit", "-10")
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
        mockMvc.perform(get("/usage06/hello3/events")
                        .param("id", "2")
//                .param("name", "spring")
                        .param("limit", "-10")
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void hello4() throws Exception {

        mockMvc.perform(get("/usage06/hello4/events")
                .param("id", "2")
                .param("name", "spring")
                .param("limit", "-10")
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
        mockMvc.perform(get("/usage06/hello4/events")
                .param("id", "-10")
                .param("name", "spring")
                .param("limit", "-10")
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void hello5() throws Exception {
        mockMvc.perform(get("/usage06/common/505/hello5/events")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("event-505"))
        ;
    }
}