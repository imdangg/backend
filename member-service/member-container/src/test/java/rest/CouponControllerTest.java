package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.jwt.JwtTokenProvider;
import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.ports.output.CouponRepository;
import com.project.imdang.member.service.domain.dto.coupon.IssueMemberCouponCommand;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static rest.TestData.*;
import static rest.TestData.memberId;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfiguration.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CouponRepository couponRepository;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MemberCouponRepository memberCouponRepository;


    static final UUID memberId = UUID.randomUUID();

    @BeforeEach
    void initialize() {
        Mockito.when(jwtTokenProvider.verifyToken(accessToken))
                .thenReturn(true);

        Mockito.when(jwtTokenProvider.extractSubject(accessToken))
                .thenReturn(String.valueOf(memberId));
    }


    @Transactional
    @Test
    void list() throws Exception{
        //given
        memberCouponRepository.saveAll(IntStream.range(0, 3)
                        .mapToObj(i -> {
                            return MemberCoupon.builder()
                                    .couponId(new CouponId(UUID.randomUUID()))
                                    .used(Boolean.FALSE)
                                    .memberId(new MemberId(memberId))
                                    .createdAt(ZonedDateTime.now()).build();
                        }).collect(Collectors.toList()));
        //when
        //then
        mockMvc.perform(get("/coupons/{memberId}", memberId)
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.couponCount").value(3))
                .andDo(print())
                .andReturn();
    }

    @Transactional
    @Test
    void issueCouponWelcome() throws Exception {
        //given
        when(couponRepository.findByName("Welcome")).thenReturn(Optional.ofNullable(welcomeCoupon));
        when(memberRepository.findById(new MemberId(memberId))).thenReturn(Optional.ofNullable(member));

        IssueMemberCouponCommand issueMemberCouponCommand = new IssueMemberCouponCommand(memberId, "Welcome");

        //when
        //then
        mockMvc.perform(post("/coupons/issue")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(issueMemberCouponCommand))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Transactional
    @Test
    void issueCouponFirstCheerUp() throws Exception {
        //given
        when(couponRepository.findByName("FirstCheerUp")).thenReturn(Optional.ofNullable(firstCoupon));
        when(memberRepository.findById(new MemberId(memberId))).thenReturn(Optional.ofNullable(member_2));
        IssueMemberCouponCommand issueMemberCouponCommand = new IssueMemberCouponCommand(memberId, "FirstCheerUp");

        //when
        //then
        mockMvc.perform(post("/coupons/issue")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(issueMemberCouponCommand))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Transactional
    @Test
    void issueCouponSecondCheerUp() throws Exception {
        //given
        when(couponRepository.findByName("SecondCheerUp")).thenReturn(Optional.ofNullable(secondCoupon));
        when(memberRepository.findById(new MemberId(memberId))).thenReturn(Optional.ofNullable(member_3));
        IssueMemberCouponCommand issueMemberCouponCommand = new IssueMemberCouponCommand(memberId, "SecondCheerUp");

        //when
        //then
        mockMvc.perform(post("/coupons/issue")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(issueMemberCouponCommand))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Transactional
    @Test
    void issueCouponThirdCheerUp() throws Exception {
        //given
        when(couponRepository.findByName("ThirdCheerUp")).thenReturn(Optional.ofNullable(thirdCoupon));
        when(memberRepository.findById(new MemberId(memberId))).thenReturn(Optional.ofNullable(member_4));
        IssueMemberCouponCommand issueMemberCouponCommand = new IssueMemberCouponCommand(memberId, "ThirdCheerUp");

        //when
        //then
        mockMvc.perform(post("/coupons/issue")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(issueMemberCouponCommand))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

//    @Test
//    @Transactional
//    void useCoupon() throws Exception{
//        //given
//
//
//        UseMemberCouponCommand useMemberCouponCommand = new UseMemberCouponCommand(memberId);
//
//        //when
//        //then
//        mockMvc.perform(post("/coupons/use")
//                        .content(objectMapper.writeValueAsString(useMemberCouponCommand))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.memberCouponId").value(1))
//                .andDo(print())
//                .andReturn();
//    }

//    @Test
//    @Transactional
//    void useCouponErrorCase() throws Exception{
//        //given
//        memberCouponRepository.saveAll(IntStream.range(0, 3)
//                .mapToObj(i -> {
//                    return MemberCoupon.builder()
//                            .couponId(new CouponId(UUID.randomUUID()))
//                            .used(Boolean.TRUE)
//                            .memberId(new MemberId(memberId))
//                            .createdAt(ZonedDateTime.now()).build();
//                }).collect(Collectors.toList()));
//
//        UseMemberCouponCommand useMemberCouponCommand = new UseMemberCouponCommand(memberId);
//
//        //when
//        //then
//        mockMvc.perform(post("/coupons/use")
//                        .content(objectMapper.writeValueAsString(useMemberCouponCommand))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andDo(print())
//                .andReturn();
//    }
}
