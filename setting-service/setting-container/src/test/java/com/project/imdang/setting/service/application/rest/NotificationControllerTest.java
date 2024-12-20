package com.project.imdang.setting.service.application.rest;

import com.project.imdang.domain.jwt.JwtTokenProvider;
import com.project.imdang.setting.service.domain.SettingServiceApplication;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = SettingServiceApplication.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void listUnchecked() throws Exception {

        // given
        String token = "token";
        String memberId = UUID.randomUUID().toString();
        Mockito.when(jwtTokenProvider.verifyToken(token))
                .thenReturn(true);
        Mockito.when(jwtTokenProvider.extractSubject(token))
                .thenReturn(memberId);

        mockMvc.perform(get("/notifications/unchecked")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}
