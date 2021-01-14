package com.harm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Usage02MediaTypeTest implements SimpleTestSupport {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello1() throws Exception {
        simpleNegativeTest(this.mockMvc, "/usage02/hello1", 415);
        simplePositiveTest(this.mockMvc, "/usage02/hello1", MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void hello2() throws Exception {
        simpleNegativeTest(this.mockMvc, "/usage02/hello2", 415);
        simplePositiveTest(this.mockMvc, "/usage02/hello2", MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void hello3() throws Exception {
        simplePositiveTest(this.mockMvc, "/usage02/hello3", MediaType.APPLICATION_JSON_UTF8);
    }
}