package com.project.imdang.setting.service.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.jwt.JwtTokenProvider;
import com.project.imdang.setting.service.domain.SettingServiceApplication;
import com.project.imdang.setting.service.domain.dto.AgreeTermsCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = SettingServiceApplication.class)
public class TermsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/terms")
//                        .param("pageNumber", "0")
//                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void agree() throws Exception {

        // given
        String token = "token";
        String memberId = UUID.randomUUID().toString();
        Mockito.when(jwtTokenProvider.verifyToken(token))
                .thenReturn(true);
        Mockito.when(jwtTokenProvider.extractSubject(token))
                .thenReturn(memberId);

        AgreeTermsCommand agreeTermsCommand = AgreeTermsCommand.builder()
                .termsIds(Set.of(1L, 2L))
//                .memberId(UUID.randomUUID())
                .build();
        mockMvc.perform(post("/terms/agree")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agreeTermsCommand)))
                .andExpect(status().isOk());
    }
}
