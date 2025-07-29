package com.project.imdang.insight;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.application.dto.insight.CreateInsightRequest;
import com.project.imdang.insight.application.dto.insight.DeleteInsightRequest;
import com.project.imdang.insight.application.dto.insight.UpdateInsightRequest;
import com.project.imdang.insight.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.member.domain.client.MemberData;
import com.project.imdang.member.persistence.provider.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

import static com.project.imdang.common.application.constant.RequestPath.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.project.imdang.member.domain.client.MemberDataResolver;

@AutoConfigureMockMvc
@SpringBootTest
@Sql(scripts = "/imdang_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class InsightControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;


    private final String memberToken = "member-token";

    @MockBean
    private MemberDataResolver memberDataResolver;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @BeforeEach
    void init() {
        // given
        Mockito.doNothing().when(jwtTokenProvider).validate(memberToken);
        Map<String, Object> mockClaims = new HashMap<>();
        mockClaims.put("memberId", TestData.memberId.toString());

        Mockito.when(tokenProvider.extractClaims(memberToken))
                .thenReturn(mockClaims);
    }

    @Test
    void list() throws Exception {
        MvcResult result = mockMvc.perform(get(LIST_INSIGHT)
                        .header("Authorization", "Bearer " + memberToken)
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        TestUtils.printPrettyJson(responseBody);
    }

    @Test
    void listByDate() throws Exception {
        MvcResult result = mockMvc.perform(get(LIST_INSIGHT_BY_DATE)
                        .header("Authorization", "Bearer " + memberToken)
                        .param("date", "2025-06-29")
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        TestUtils.printPrettyJson(responseBody);
    }

    @Test
    void listByDistrict() throws Exception {
        MvcResult result = mockMvc.perform(get(LIST_INSIGHT_BY_DISTRICT)
                        .header("Authorization", "Bearer " + memberToken)
                        .param("siGunGu", TestData.address.getSiGunGu())
                        .param("eupMyeonDong", TestData.address.getEupMyeonDong())
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        TestUtils.printPrettyJson(responseBody);
    }

    @Test
    void listByApartmentComplex() throws Exception {
        MvcResult result = mockMvc.perform(get(LIST_INSIGHT_BY_APARTMENT_COMPLEX)
                        .header("Authorization", "Bearer " + memberToken)
                        .param("apartmentComplexName", TestData.apartmentComplex.getName())
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        TestUtils.printPrettyJson(responseBody);
    }

    @Test
    void listCreatedByMe() throws Exception {
        MvcResult result = mockMvc.perform(get(LIST_INSIGHT_CREATED_BY_ME)
                        .header("Authorization", "Bearer " + memberToken)
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        TestUtils.printPrettyJson(responseBody);
    }

    @Test
    void listByMyVisitedApartmentComplex() throws Exception {
        MvcResult result = mockMvc.perform(get(LIST_APARTMENT_COMPLEX_OF_INSIGHT_CREATED_BY_ME)
                        .header("Authorization", "Bearer " + memberToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        TestUtils.printPrettyJson(responseBody);
    }


    @Test
    void detail() throws Exception {
        String insightId = "6383c5ad-7407-4f77-b095-0b7f817e389d";
        given(memberDataResolver.resolve(any(MemberId.class)))
                .willReturn(Optional.of(
                        MemberData.builder()
                                .memberId(UUID.fromString("fb2b3270-65e3-4006-a994-71e74a676a8a"))
                                .nickname("테스트유저")
                                .birthDate("19981215")
                                .gender("0")
                                .deviceToken("dummy-token")
                                .accusedCount(0L)
                                //.insightCount(5L)
                                .latestInsightCreateDate(LocalDate.of(2025, 6, 28))
                                .build()
                ));

        MvcResult result = mockMvc.perform(get("/insights/detail")
                        .header("Authorization", "Bearer " + memberToken)
                        .param("insightId", insightId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        TestUtils.printPrettyJson(responseBody);
    }

    @Test
    void createInsight() throws Exception {

        // given
        CreateInsightRequest createInsightrequest = new TestData().createInsightRequest();
        String valueAsString = objectMapper.writeValueAsString(createInsightrequest);
        byte[] bytes = "content".getBytes();
        List<MockMultipartFile> mainImages = List.of(
                new MockMultipartFile("mainImages", "img1.png", "image/png", "img1".getBytes()),
                new MockMultipartFile("mainImages", "img2.png", "image/png", "img2".getBytes())
        );
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/insights/create")
                        .file(mainImages.get(0))
                        .file(mainImages.get(1))
                        .file(new MockMultipartFile(
                                "createInsightRequest", "createInsightRequest.json", "application/json", valueAsString.getBytes(StandardCharsets.UTF_8)))
                        .header("Authorization", "Bearer " + memberToken)
                        .with(csrf()))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void updateInsight() throws Exception {

        // given
        UUID insightId = UUID.fromString("8f3fb78d-d2b8-4644-9a2c-65b615a0ea5b");
        UpdateInsightRequest updateInsightRequest = new TestData(insightId).updateInsightRequest();
        String valueAsString = objectMapper.writeValueAsString(updateInsightRequest);
        byte[] bytes = "content".getBytes();
        List<MockMultipartFile> mainImages = List.of(
                new MockMultipartFile("mainImages", "img1.png", "image/png", "img1".getBytes()),
                new MockMultipartFile("mainImages", "img2.png", "image/png", "img2".getBytes())
        );

        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/insights/update")
                        .file(mainImages.get(0))
                        .file(mainImages.get(1))
                        .file(new MockMultipartFile("updateInsightRequest", "updateInsightRequest.json", "application/json", valueAsString.getBytes(StandardCharsets.UTF_8)))
                        .header("Authorization", "Bearer " + memberToken)
                        .with(csrf()))
                .andExpect(status().isOk()) // HTTP 상태 확인
//                .andExpect(jsonPath("$.insightId", notNullValue()));
                .andDo(print());
    }

    @Test
    void recommendInsight() throws Exception {
        UUID insightId = UUID.fromString("f509ce55-a67a-4c97-8846-0dee0c754c38");
        RecommendInsightCommand recommendInsightCommand = new TestData(insightId).recommendInsightCommand();
        mockMvc.perform(post("/insights/recommend")
                        .header("Authorization", "Bearer " + memberToken)
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
                        .header("Authorization", "Bearer " + memberToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accuseInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void deleteInsight() throws Exception {
        UUID insightId = UUID.fromString("8f3fb78d-d2b8-4644-9a2c-65b615a0ea5b");
        DeleteInsightRequest deleteInsightRequest = new TestData(insightId).deleteInsightRequest();
        mockMvc.perform(post("/insights/delete")
                        .header("Authorization", "Bearer " + memberToken)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteInsightRequest))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                ;
    }
}
