package com.harm.controller;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public interface SimpleTestSupport {

    default void simplePositiveTest(MockMvc mockMvc, String url) throws Exception {
        mockMvc
                .perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
    }
    default void simplePositiveTest(MockMvc mockMvc, String url, MediaType mediaType) throws Exception {
        mockMvc
                .perform(get(url).contentType(mediaType))
                .andDo(print())
                .andExpect(status().isOk());
    }
    default void simplePositiveTest(MockMvc mockMvc, String url, String expectBody) throws Exception {
        mockMvc
                .perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectBody));
    }
    default void simpleNegativeTest(MockMvc mockMvc, String url) throws Exception {
        mockMvc
                .perform(get(url))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    default void simpleNegativeTest(MockMvc mockMvc, String url, int status) throws Exception {
        mockMvc
                .perform(get(url))
                .andDo(print())
                .andExpect(status().is(status));
    }
}
