package com.project.imdang.insight;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.member.persistence.provider.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfiguration.class)
public class BookmarkedInsightControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private final String memberToken = "member-token";

    @BeforeEach
    void init() {
//        // given
//        Mockito.when(jwtTokenProvider.verifyToken(memberToken))
//                .thenReturn(true);
//        Mockito.when(jwtTokenProvider.extractSubject(memberToken))
//                .thenReturn(String.valueOf(TestData.memberId));
    }

    @Test
    void listDistrict() throws Exception {
        mockMvc.perform(get("/my-insights/districts")
                        .header("Authorization", "Bearer " + memberToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void listApartmentComplexByDistrict() throws Exception {
        mockMvc.perform(get("/my-insights/by-district/apartment-complexes")
                        .header("Authorization", "Bearer " + memberToken)
                        .param("siDo", "서울시")
                        .param("siGunGu", "강남구")
                        .param("eupMyeonDong", "신논현동")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void list() throws Exception {
        mockMvc.perform(get("/my-insights")
                        .header("Authorization", "Bearer " + memberToken)
//                        .param("onlyMine", "TRUE")
                        .param("siDo", "서울시")
                        .param("siGunGu", "강남구")
                        .param("eupMyeonDong", "신논현동")
                        .param("pageNumber", "1")
                        .param("pageSize", "5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content.length()").value(1))
                .andDo(print())
                .andReturn();
    }

    @Test
    void listCreatedByMe() throws Exception {
        mockMvc.perform(get("/my-insights/created-by-me")
                        .header("Authorization", "Bearer " + memberToken)
                        .param("pageNumber", "1")
                        .param("pageSize", "5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}
