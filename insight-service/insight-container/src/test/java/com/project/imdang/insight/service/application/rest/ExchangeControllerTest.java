package com.project.imdang.insight.service.application.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.insight.service.domain.InsightServiceApplication;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = InsightServiceApplication.class)
public class ExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void request() throws Exception {
        UUID requestedInsightId = UUID.randomUUID();
        UUID requestMemberId = UUID.randomUUID();
        UUID requestMemberInsightId = UUID.randomUUID();
        RequestExchangeInsightCommand requestExchangeInsightCommand = RequestExchangeInsightCommand.builder()
                .requestedInsightId(requestedInsightId)
                .requestMemberId(requestMemberId)
                .requestMemberInsightId(requestMemberInsightId)
                .build();
        mockMvc.perform(post("/exchanges/request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestExchangeInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    public void accept() {

    }

    @Test
    public void reject() {

    }
}
