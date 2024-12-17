package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.persistence.entity.CouponEntity;
import com.project.imdang.member.persistence.repository.CouponJpaRespository;
import com.project.imdang.member.service.domain.dto.coupon.IssueMemberCouponCommand;
import com.project.imdang.member.service.domain.dto.coupon.UseMemberCouponCommand;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfiguration.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MemberCouponRepository memberCouponRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CouponJpaRespository couponJpaRespository;

    static final UUID memberId = UUID.randomUUID();

    @BeforeEach
    void initialize() {
        memberRepository.save(Member.builder()
                .id(new MemberId(memberId))
                .nickname("imdang").build());
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
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.couponCount").value(3))
                .andDo(print())
                .andReturn();
    }

    @Transactional
    @Test
    void issueCoupon() throws Exception {
        //given
        UUID couponId = UUID.randomUUID();
        couponJpaRespository.save(CouponEntity.builder()
                        .id(couponId).name("Welcome").build());

        IssueMemberCouponCommand issueMemberCouponCommand = new IssueMemberCouponCommand(memberId, couponId);

        //when
        //then
        mockMvc.perform(post("/coupons/issue")
                        .content(objectMapper.writeValueAsString(issueMemberCouponCommand))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @Transactional
    void useCoupon() throws Exception{
        //given
        memberCouponRepository.saveAll(IntStream.range(0, 3)
                .mapToObj(i -> {
                    return MemberCoupon.builder()
                            .couponId(new CouponId(UUID.randomUUID()))
                            .used(Boolean.FALSE)
                            .memberId(new MemberId(memberId))
                            .createdAt(ZonedDateTime.now()).build();
                }).collect(Collectors.toList()));

        UseMemberCouponCommand useMemberCouponCommand = new UseMemberCouponCommand(memberId);

        //when
        //then
        mockMvc.perform(post("/coupons/use")
                        .content(objectMapper.writeValueAsString(useMemberCouponCommand))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberCouponId").value(1))
                .andDo(print())
                .andReturn();
    }

    @Test
    @Transactional
    void useCouponErrorCase() throws Exception{
        //given
        memberCouponRepository.saveAll(IntStream.range(0, 3)
                .mapToObj(i -> {
                    return MemberCoupon.builder()
                            .couponId(new CouponId(UUID.randomUUID()))
                            .used(Boolean.TRUE)
                            .memberId(new MemberId(memberId))
                            .createdAt(ZonedDateTime.now()).build();
                }).collect(Collectors.toList()));

        UseMemberCouponCommand useMemberCouponCommand = new UseMemberCouponCommand(memberId);

        //when
        //then
        mockMvc.perform(post("/coupons/use")
                        .content(objectMapper.writeValueAsString(useMemberCouponCommand))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
    }
}