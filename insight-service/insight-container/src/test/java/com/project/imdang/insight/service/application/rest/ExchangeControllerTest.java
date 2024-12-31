package com.project.imdang.insight.service.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.jwt.JwtTokenProvider;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.project.imdang.insight.service.application.rest.TestData.requestMemberId;
import static com.project.imdang.insight.service.application.rest.TestData.requestedMemberId;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfiguration.class)
public class ExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private String requestMemberToken = "request-member-token";
    private String requestedMemberToken = "requested-member-token";


    @BeforeEach
    void init() {
        // given
        Mockito.when(jwtTokenProvider.verifyToken(requestMemberToken))
                .thenReturn(true);
        Mockito.when(jwtTokenProvider.extractSubject(requestMemberToken))
                .thenReturn(String.valueOf(requestMemberId));

        Mockito.when(jwtTokenProvider.verifyToken(requestedMemberToken))
                .thenReturn(true);
        Mockito.when(jwtTokenProvider.extractSubject(requestedMemberToken))
                .thenReturn(String.valueOf(requestedMemberId));
    }

//    @Test
    public void request() throws Exception {
        UUID insightId = UUID.fromString("111512ab-a7b6-43f9-bd61-16ee2cded7d9");
        UUID requestMemberInsightId = UUID.fromString("fc16cf45-2e8d-4cf2-b260-f7cac33820da");
        RequestExchangeInsightCommand requestExchangeInsightCommand
                = new TestData(insightId).requestExchangeInsightCommand(requestMemberInsightId, null) ;

        mockMvc.perform(post("/exchanges/request")
                        .header("Authorization", "Bearer " + requestMemberToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestExchangeInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.exchangeRequestId", notNullValue()));
    }

//    @Test
    public void request_with_coupon() throws Exception {
        UUID insightId = UUID.fromString("111512ab-a7b6-43f9-bd61-16ee2cded7d9");
        RequestExchangeInsightCommand requestExchangeInsightCommand
                = new TestData(insightId).requestExchangeInsightCommand(null, 1L) ;

        mockMvc.perform(post("/exchanges/request")
                        .header("Authorization", "Bearer " + requestMemberToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestExchangeInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.exchangeRequestId", notNullValue()));
    }

//    @Test
    public void accept() throws Exception {
        AcceptExchangeRequestCommand acceptExchangeRequestCommand = AcceptExchangeRequestCommand.builder()
                .exchangeRequestId(UUID.fromString("a4c9a9e8-2577-4a4d-9bf1-3f76687336dd"))
                .requestedMemberId(requestedMemberId)
                .build();
        mockMvc.perform(post("/exchanges/accept")
                        .header("Authorization", "Bearer " + requestedMemberToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(acceptExchangeRequestCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.exchangeRequestId", notNullValue()));
    }

//    @Test
    public void reject() throws Exception {
        RejectExchangeRequestCommand rejectExchangeRequestCommand = RejectExchangeRequestCommand.builder()
                .exchangeRequestId(UUID.fromString("a4c9a9e8-2577-4a4d-9bf1-3f76687336dd"))
                .requestedMemberId(requestedMemberId)
                .build();
        mockMvc.perform(post("/exchanges/reject")
                        .header("Authorization", "Bearer " + requestedMemberToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rejectExchangeRequestCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.exchangeRequestId", notNullValue()));
    }
}
