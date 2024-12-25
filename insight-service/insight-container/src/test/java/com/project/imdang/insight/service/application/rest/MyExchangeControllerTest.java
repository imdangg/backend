package com.project.imdang.insight.service.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.project.imdang.insight.service.application.rest.TestData.requestMemberId;
import static com.project.imdang.insight.service.application.rest.TestData.requestedMemberId;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfiguration.class)
public class MyExchangeControllerTest {

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

    @Test
    void listRequestedByMe() throws Exception {
        mockMvc.perform(get("/my-exchanges/requested-by-me")
                        .header("Authorization", "Bearer " + requestMemberToken)
                        .param("exchangeRequestStatus", "REJECTED")
//                        .param("pageNumber", "0")
//                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content.length()").value(1))
                .andDo(print())
                .andReturn();
    }

    @Test
    void listRequestedByOthers() throws Exception {
        mockMvc.perform(get("/my-exchanges/requested-by-others")
                        .header("Authorization", "Bearer " + requestMemberToken)
                        .param("exchangeRequestStatus", "REJECTED")
//                        .param("pageNumber", "0")
//                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content.length()").value(1))
                .andDo(print())
                .andReturn();
    }
}
