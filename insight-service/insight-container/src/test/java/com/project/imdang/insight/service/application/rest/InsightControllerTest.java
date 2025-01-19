package com.project.imdang.insight.service.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.jwt.JwtTokenProvider;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static com.project.imdang.insight.service.application.rest.TestData.requestMemberId;
import static com.project.imdang.insight.service.application.rest.TestData.requestedMemberId;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfiguration.class)
class InsightControllerTest {

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
    void list() throws Exception {
        mockMvc.perform(get("/insights")
                        .header("Authorization", "Bearer " + requestMemberToken)
//                        .param("pageNumber", "0")
//                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void listByApartmentComplex() throws Exception {
        mockMvc.perform(get("/insights/by-apartment-complex")
                        .param("apartmentComplexName", TestData.apartmentComplex.getName())
                        .header("Authorization", "Bearer " + requestMemberToken)
//                        .param("pageNumber", "0")
//                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content.length()").value(1))
                .andDo(print())
                .andReturn();
    }

    @Test
    void detail() throws Exception {
        String insightId = "f509ce55-a67a-4c97-8846-0dee0c754c38";
        mockMvc.perform(get("/insights/detail")
                        .header("Authorization", "Bearer " + requestMemberToken)
                        .param("insightId", insightId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void createInsight() throws Exception {

        // given
        CreateInsightCommand createInsightCommand = new TestData().createInsightCommand();
        String valueAsString = objectMapper.writeValueAsString(createInsightCommand);
        byte[] bytes = "content".getBytes();
        MockMultipartFile mainImage = new MockMultipartFile("mainImage", "", "text/plain", bytes);
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/insights/create")
                        .file(mainImage)
                        .file(new MockMultipartFile("createInsightCommand", "", "application/json", valueAsString.getBytes(StandardCharsets.UTF_8)))
                        .header("Authorization", "Bearer " + requestMemberToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void updateInsight() throws Exception {

        // given
        UUID insightId = UUID.fromString("b434b945-5e39-4439-b0eb-ff953b777118");
        UpdateInsightCommand updateInsightCommand = new TestData(insightId).updateInsightCommand();
        String valueAsString = objectMapper.writeValueAsString(updateInsightCommand);
        byte[] bytes = "content".getBytes();
        MockMultipartFile mainImage = new MockMultipartFile("mainImage", "", "text/plain", bytes);

        // TODO - QUERY CHECK
        // Hibernate: update insight set access=?,accused_count=?,dong=?,si_gun_gu=?,name=?,complex_environment=?,complex_facility=?,contents=?,created_at=?,favorable_news=?,infra=?,main_image=?,member_id=?,recommended_count=?,score=?,summary=?,title=?,view_count=?,visit_at=?,visit_method=? where id=?
        // Hibernate: update snapshot set access=?,dong=?,si_gun_gu=?,name=?,complex_environment=?,complex_facility=?,contents=?,favorable_news=?,infra=?,main_image=?,member_id=?,summary=?,title=?,visit_at=?,visit_method=? where id=?
        // when
        // then
        mockMvc.perform(multipart("/insights/update")
                        .file(mainImage)
                        .file(new MockMultipartFile("updateInsightCommand", "", "application/json", valueAsString.getBytes(StandardCharsets.UTF_8)))
                        .header("Authorization", "Bearer " + requestMemberToken))
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void recommendInsight() throws Exception {
        UUID insightId = UUID.fromString("f509ce55-a67a-4c97-8846-0dee0c754c38");
        RecommendInsightCommand recommendInsightCommand = new TestData(insightId).recommendInsightCommand();
        mockMvc.perform(post("/insights/recommend")
                        .header("Authorization", "Bearer " + requestMemberToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recommendInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void accuseInsight() throws Exception {
        UUID insightId = UUID.fromString("b434b945-5e39-4439-b0eb-ff953b777118");
        AccuseInsightCommand accuseInsightCommand = new TestData(insightId).accuseInsightCommand();
        mockMvc.perform(post("/insights/accuse")
                        .header("Authorization", "Bearer " + requestMemberToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accuseInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void deleteInsight() throws Exception {
        UUID insightId = UUID.fromString("b434b945-5e39-4439-b0eb-ff953b777118");
        DeleteInsightCommand deleteInsightCommand = new TestData(insightId).deleteInsightCommand();
        mockMvc.perform(post("/insights/delete")
                        .header("Authorization", "Bearer " + requestedMemberToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }
}
