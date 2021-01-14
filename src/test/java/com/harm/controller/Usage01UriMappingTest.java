package com.harm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Usage01UriMappingTest implements SimpleTestSupport{
    @Autowired
    MockMvc mockMvc;


    @Test
    public void hello1() throws Exception {
        simplePositiveTest(mockMvc, "/usage01/hello1", "hello1");
        mockMvc.perform(head("/usage01/hello1"))
                .andDo(print())
                .andExpect(status().isOk())
                ;
    }

    @Test
    public void hello2() throws Exception {
        simplePositiveTest(mockMvc, "/usage01/hello2/foo", "hello2");
        simpleNegativeTest(mockMvc, "/usage01/hello2/foobar");
    }

    @Test
    public void hello3() throws Exception {
        simplePositiveTest(mockMvc, "/usage01/hello3/manywords", "hello3");
        simpleNegativeTest(mockMvc, "/usage01/hello3/manywords/butnotmanypaths");
    }

    @Test
    public void hello4() throws Exception {
        simplePositiveTest(mockMvc, "/usage01/hello4/manywords/manypaths", "hello4");
    }

    @Test
    public void hello5() throws Exception {
        simplePositiveTest(mockMvc, "/usage01/hello5/thisisvariable", "thisisvariable");
        simpleNegativeTest(mockMvc, "/usage01/hello5/thisisvariable2");
    }

    @Test
    public void hello6() throws Exception {
        simpleNegativeTest(mockMvc, "/usage01/hello6.xml");
    }

}