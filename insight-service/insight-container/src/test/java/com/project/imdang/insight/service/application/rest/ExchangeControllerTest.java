package com.project.imdang.insight.service.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.jwt.JwtTokenProvider;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static com.project.imdang.insight.service.application.rest.TestData.memberCouponId;
import static com.project.imdang.insight.service.application.rest.TestData.requestMemberId;
import static com.project.imdang.insight.service.application.rest.TestData.requestMemberInsightId;
import static com.project.imdang.insight.service.application.rest.TestData.requestMemberSnapshot;
import static com.project.imdang.insight.service.application.rest.TestData.requestedInsightId;
import static com.project.imdang.insight.service.application.rest.TestData.requestedMemberId;
import static com.project.imdang.insight.service.application.rest.TestData.requestedSnapshot;
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
    @MockBean
    private SnapshotRepository snapshotRepository;

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

        Mockito.when(snapshotRepository.findLatestByInsightId(new InsightId(requestedInsightId)))
                .thenReturn(Optional.ofNullable(requestedSnapshot));
        Mockito.when(snapshotRepository.findLatestByInsightId(new InsightId(requestMemberInsightId)))
                .thenReturn(Optional.ofNullable(requestMemberSnapshot));
    }

//    @Test
    public void request() throws Exception {
        RequestExchangeInsightCommand requestExchangeInsightCommand = RequestExchangeInsightCommand.builder()
                .requestedInsightId(requestedInsightId)
                .requestMemberId(requestMemberId)
                .requestMemberInsightId(requestMemberInsightId)
                .build();
        mockMvc.perform(post("/exchanges/request")
                        .header("Authorization", "Bearer " + requestMemberToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestExchangeInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.exchangeRequestId", notNullValue()));
    }

//    @Test
    public void request_with_coupon() throws Exception {
        RequestExchangeInsightCommand requestExchangeInsightCommand = RequestExchangeInsightCommand.builder()
                .requestedInsightId(requestedInsightId)
                .requestMemberId(requestMemberId)
                .memberCouponId(memberCouponId)
                .build();
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
                .exchangeRequestId(UUID.fromString("6e12ef7b-4a0a-4ae2-a3c2-492a96e0da21"))
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
                .exchangeRequestId(UUID.fromString("6e12ef7b-4a0a-4ae2-a3c2-492a96e0da21"))
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
