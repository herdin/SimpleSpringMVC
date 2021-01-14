package com.harm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LocalDateTimeControllerTest {
    private Logger logger = LoggerFactory.getLogger(LocalDateTimeControllerTest.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void keyvalue1() throws Exception {
        /**
         * send -------------
         * data type : key=value
         * data location : body
         *          * content type : none
         *          * date data format : none
         *          * recv -------------
         *          * handler annotation : @ModelAttribute
         *          * value object annotation : none
         *          *
         *          * plain type 은 받을 수 있다.
         */
        mockMvc.perform(post("/model-attribute")
                .param("id", "12")
                .param("name", "hello, name")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("12"))
                .andExpect(jsonPath("$.name").value("hello, name"))
        ;
    }

    @Test
    public void keyvalue2() throws Exception {
        /**
         * send -------------
         * data type : key=value
         * data location : body
         * content type : none
         * date data format : yyyyMMddHHmmss
         * recv -------------
         * handler annotation : @ModelAttribute
         * value object annotation : none
         *
         * 20200318111213 -> LocalDateTime 바인딩하다가 오류가 난다.
         * 400 BAD_REQUEST, rejected value, [typeMismatch.composite.startTime,typeMismatch.startTime,typeMismatch.java.time.LocalDateTime,typeMismatch]
         */
        mockMvc.perform(post("/model-attribute")
                .param("id", "12")
                .param("name", "hello, name")
                .param("startTime", "20200318111213")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void keyvalue3() throws Exception {
        /**
         * send -------------
         * data type : key=value
         * data location : body
         * content type : none
         * date data format : yyyyMMddHHmmss
         * recv -------------
         * handler annotation : @ModelAttribute
         * value object annotation : @DateTimeFormat("yyyyMMddHHmmss")
         *
         * 성공
         */
        mockMvc.perform(post("/model-attribute/date-time-format")
                .param("id", "12")
                .param("name", "hello, name")
                .param("startTime", "20200318111213")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startTime").value("2020-03-18T11:12:13"))
        ;
    }

    @Test
    public void keyvalue4() throws Exception {
        /**
         * send -------------
         * data type : key=value
         * data location : body
         * content type : none
         * date data format : yyyy-MM-ddTHH:mm:ss
         * recv -------------
         * handler annotation : @ModelAttribute
         * value object annotation : @DateTimeFormat("yyyy-MM-dd'T'HH:mm:ss")
         *
         * 다른 포멧으로 보내보내더라도 포메팅만 맞으면 성공
         */
        mockMvc.perform(post("/model-attribute/date-time-format")
                .param("id", "12")
                .param("name", "hello, name")
                .param("startTime", "20200318111213")
                .param("endTime", "2020-03-18T11:12:13")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startTime").value("2020-03-18T11:12:13"))
                .andExpect(jsonPath("$.endTime").value("2020-03-18T11:12:13"))
        ;
    }

    @Test
    public void json1() throws Exception {
        /**
         * send -------------
         * data type : json
         * data location : body
         * content type : none/model-attribute/date-time-format
         * date data format : yyyyMMddHHmmss
         * recv -------------
         * handler annotation : @ModelAttribute
         * value object annotation : none
         *
         * 에러는 나지 않지만, 바인딩하지 못한다. @ModelAttribute 는 key=value 를 찾나보다.
         */
        String json = "{\"id\":12,\"name\":\"hello, name\",\"startTime\":\"20200318181129\",\"endTime\":null}";
        logger.debug("send data -> {}", json);
        mockMvc.perform(post("/model-attribute")
                .content(json)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isEmpty())
        ;
    }

    @Test
    public void json2() throws Exception {
        /**
         * send -------------
         * data type : json
         * data location : body
         * content type : application/json
         * date data format : yyyyMMddHHmmss
         * recv -------------
         * handler annotation : @ModelAttribute
         * value object annotation : none
         *
         * content type 을 보내도, 에러는 나지 않지만, 바인딩하지 못한다. @ModelAttribute 는 key=value 를 찾나보다.
         */
        String json = "{\"id\":12,\"name\":\"hello, name\",\"startTime\":\"20200318181129\",\"endTime\":null}";
        logger.debug("send data -> {}", json);
        mockMvc.perform(post("/model-attribute")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isEmpty())
        ;
    }

    @Test
    public void json3() throws Exception {
        /**
         * send -------------
         * data type : json
         * data location : body
         * content type : application/json
         * date data format : yyyyMMddHHmmss
         * recv -------------
         * handler annotation : @RequestBody
         * value object annotation : none
         *
         * @RequestBody binding 객체에 format 이 없는 경우, JSON parse error 가 난다. 20200318181129 String -> LocalDateTime 를 어떻게 컨버전할지 모름.
         */
        String json = "{\"id\":12,\"name\":\"hello, name\",\"startTime\":\"20200318181129\",\"endTime\":null}";
        logger.debug("send data -> {}", json);
        mockMvc.perform(post("/request-body")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void json4() throws Exception {
        /**
         * send -------------
         * data type : json
         * data location : body
         * content type : application/json
         * date data format : yyyy-MM-dd'T'HH:mm:ss
         * recv -------------
         * handler annotation : @RequestBody
         * value object annotation : none
         *
         * @RequestBody binding 객체에 format 이 없지만, LocalDateTime 기본 형태(yyyy-MM-dd'T'HH:mm:ss)로 보내면, 받아진다.
         *
         */
        String json = "{\"id\":12,\"name\":\"hello, name\",\"startTime\":\"2020-03-18T18:11:29\",\"endTime\":null}";
        logger.debug("send data -> {}", json);
        mockMvc.perform(post("/request-body")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startTime").value("2020-03-18T18:11:29"))
        ;
    }

    @Test
    public void json5() throws Exception {
        /**
         * send -------------
         * data type : json
         * data location : body
         * content type : application/json
         * date data format : yyyyMMddHHmmss
         * recv -------------
         * handler annotation : @RequestBody
         * value object annotation : @DateTimeFormat(pattern = "yyyyMMddHHmmss")
         *
         * JSON parse error 가 난다. 20200318181129 String -> LocalDateTime, jackson 은 @DateTimeFormat 을 모른다.
         */
        String json = "{\"id\":12,\"name\":\"hello, name\",\"startTime\":\"20200318181129\",\"endTime\":null}";
        logger.debug("send data -> {}", json);
        mockMvc.perform(post("/request-body/date-time-format")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void json6() throws Exception {
        /**
         * send -------------
         * data type : json
         * data location : body
         * content type : application/json
         * date data format : yyyy-MM-dd'T'HH:mm:ss
         * recv -------------
         * handler annotation : @RequestBody
         * value object annotation : @DateTimeFormat(pattern = "yyyyMMddHHmmss")
         *
         * jojoldu 가 헷갈리게 했는데, jackson 은 @DateTimeFormat 랑 상관없이 String -> LocalDateTime 을 바인딩할때, 기본형태만 넣을 수 있는 것이다.
         * 뭘헷갈리게 했냐면, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") 이따구로 넣어놓고 마치 jackson 이 @DateTimeFormat 를 알아 먹는 것 처럼 써놓았다.
         */
        String json = "{\"id\":12,\"name\":\"hello, name\",\"startTime\":\"2020-03-18T18:11:29\",\"endTime\":null}";
        logger.debug("send data -> {}", json);
        mockMvc.perform(post("/request-body/date-time-format")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startTime").value("2020-03-18T18:11:29"))
        ;
    }

    @Test
    public void json7() throws Exception {
        /**
         * send -------------
         * data type : json
         * data location : body
         * content type : application/json
         * date data format : yyyyMMddHHmmss
         * recv -------------
         * handler annotation : @RequestBody
         * value object annotation : @JsonFormat(pattern = "yyyyMMddHHmmss")
         *
         * jackson 은 serial->deserial 할때, 모두 @JsonFormat 을 본다는 것을 확인했다.
         */
        String json = "{\"id\":12,\"name\":\"hello, name\",\"startTime\":\"20200318181129\",\"endTime\":null}";
        logger.debug("send data -> {}", json);
        mockMvc.perform(post("/request-body/json-format")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startTime").value("20200318181129"))
        ;
    }
}