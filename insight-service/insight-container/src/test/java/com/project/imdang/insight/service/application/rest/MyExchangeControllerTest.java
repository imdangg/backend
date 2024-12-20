package com.project.imdang.insight.service.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.insight.service.domain.InsightServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = InsightServiceApplication.class)
public class MyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listRequestedByMe() throws Exception {
        mockMvc.perform(get("/insights/by-apartment-complex")
                        .param("apartmentComplexName", "신논현 더 센트럴 푸르지오")
//                        .param("pageNumber", "0")
//                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andDo(print())
                .andReturn();
    }

    @Test
    void listRequestedByOthers() throws Exception {
        mockMvc.perform(get("/insights/by-apartment-complex")
                        .param("apartmentComplexName", "신논현 더 센트럴 푸르지오")
//                        .param("pageNumber", "0")
//                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andDo(print())
                .andReturn();
    }


}
