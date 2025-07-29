package com.project.imdang.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.ports.output.repository.MemberRepository;
import com.project.imdang.member.persistence.provider.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.project.imdang.member.TestData.accessToken;
import static com.project.imdang.member.TestData.member;
import static com.project.imdang.member.TestData.memberId;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;


    @Test
    void info() throws Exception {
        //given
//        Mockito.when(jwtTokenProvider.verifyToken(accessToken))
//                        .thenReturn(true);

        Mockito.when(jwtTokenProvider.extractSubject(accessToken))
                .thenReturn(String.valueOf(memberId));

        Mockito.when(memberRepository.findById(new MemberId(memberId)))
                        .thenReturn(Optional.ofNullable(member));
        //then
        mockMvc.perform(get("/members")
//                        .header("Authorization", "Bearer " + accessToken)
                        .param("memberId", String.valueOf(memberId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value("imdang"))
                .andExpect(jsonPath("$.deviceToken").value("fcm-token"))
                .andDo(print())
                .andReturn();
    }

    @Test
    void condition() throws Exception {
        //given
        Mockito.doNothing().when(jwtTokenProvider).validate(accessToken);

        Map<String, Object> mockClaims = new HashMap<>();
        mockClaims.put("memberId", memberId);

        Mockito.when(jwtTokenProvider.extractClaims(accessToken))
                .thenReturn(mockClaims);

        String requestBody = """
        {
            "purpose": "실거주",
            "budget": "1억 이하",
            "monthIncome": "수입 없음",
            "livingPerson": "중년부부",
            "childrenPlan": "자녀 계획 있음",
            "hopeGap": null,
            "investmentPlan": null,
            "traffic": "역세권",
            "schoolDistrict": "학원가 근접",
            "apartmentSquare": null,
            "household": null,
            "houseType": null,
            "commutingArea": "여의도",
            "infra": "대형병원",
            "environment": "공원",
            "firstPriority": "출퇴근지역,여의도",
            "secondPriority": "학군,학원가 근접",
            "thirdPriority": "교통,역세권"
        }
        """;

        //then
        mockMvc.perform(post("/members/condition")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}
