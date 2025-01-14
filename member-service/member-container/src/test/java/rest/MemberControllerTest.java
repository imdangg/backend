package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import com.project.imdang.domain.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static rest.TestData.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfiguration.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;


    @Test
    void info() throws Exception {
        //given
        Mockito.when(jwtTokenProvider.verifyToken(accessToken))
                        .thenReturn(true);

        Mockito.when(jwtTokenProvider.extractSubject(accessToken))
                .thenReturn(String.valueOf(memberId));

        Mockito.when(memberRepository.findById(new MemberId(memberId)))
                        .thenReturn(Optional.ofNullable(member));
        //then
        mockMvc.perform(get("/members")
                        .header("Authorization", "Bearer " + accessToken)
                        .param("memberId", String.valueOf(memberId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value("imdang"))
                .andExpect(jsonPath("$.deviceToken").value("fcm-token"))
                .andDo(print())
                .andReturn();
    }
}
