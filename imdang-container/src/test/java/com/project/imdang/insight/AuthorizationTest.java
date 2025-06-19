package com.project.imdang.insight;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.insight.domain.dto.insight.create.CreateInsightCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class AuthorizationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    // 모의 SecurityContext
    // 인증하지 않고 엔드포인트를 호출했을 때 '401 권한 없음' 확인
    @Test
    public void createInsightUnauthenticated() throws Exception {
        // given
        CreateInsightCommand createInsightCommand = new TestData().createInsightCommand();
        String valueAsString = objectMapper.writeValueAsString(createInsightCommand);
        byte[] bytes = "content".getBytes();
        MockMultipartFile mainImage = new MockMultipartFile("mainImage", "", "text/plain", bytes);
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/insights/create")
                        .file(mainImage)
                        .file(new MockMultipartFile("createInsightCommand", "", "application/json", valueAsString.getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser
    @Test
    public void createInsightAuthenticated_withMockUser() throws Exception {
        // given
        CreateInsightCommand createInsightCommand = new TestData().createInsightCommand();
        String valueAsString = objectMapper.writeValueAsString(createInsightCommand);
        byte[] bytes = "content".getBytes();
        MockMultipartFile mainImage = new MockMultipartFile("mainImage", "", "text/plain", bytes);
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/insights/create")
                        .file(mainImage)
                        .file(new MockMultipartFile("createInsightCommand", "", "application/json", valueAsString.getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().isOk());
    }

    @WithUserDetails("john")
    @Test
    public void createInsightAuthenticated_withDBUser() throws Exception {
        // given
        CreateInsightCommand createInsightCommand = new TestData().createInsightCommand();
        String valueAsString = objectMapper.writeValueAsString(createInsightCommand);
        byte[] bytes = "content".getBytes();
        MockMultipartFile mainImage = new MockMultipartFile("mainImage", "", "text/plain", bytes);
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/insights/create")
                        .file(mainImage)
                        .file(new MockMultipartFile("createInsightCommand", "", "application/json", valueAsString.getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().isOk());
    }
}
