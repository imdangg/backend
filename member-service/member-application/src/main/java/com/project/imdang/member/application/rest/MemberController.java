package com.project.imdang.member.application.rest;

import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.Purpose;
import com.project.imdang.member.application.dto.member.OnboardingRequest;
import com.project.imdang.member.application.dto.member.JoinRequest;
import com.project.imdang.member.application.dto.member.WithdrawRequest;
import com.project.imdang.member.domain.dto.member.*;
import com.project.imdang.member.domain.ports.input.service.MemberApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.project.imdang.common.application.constant.RequestPath.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "MemberController", description = "회원 API")
public class MemberController {
    
    private final MemberApplicationService memberApplicationService;

    @Operation(description = "마이 페이지 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "마이 페이지 조회 성공")
    })
    @GetMapping(DETAIL_MY_PAGE)
    public ApiResponse<MyPageInfoResult> detailMyPage(@AuthenticationPrincipal UUID memberId) {
        MyPageInfoResult myPageInfoResult = memberApplicationService.detailMyPage(new MemberId(memberId));
        log.info("MyPage of Member[id : {}] is viewed.", memberId);
        return ApiResponse.success(myPageInfoResult);
    }

    @Operation(description = "회원 정보 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "회원 정보 조회 성공")
    })
    @GetMapping(DETAIL_MEMBER)
    public ApiResponse<MemberResult> detail(@RequestParam("memberId") UUID memberId) {
        MemberResult memberResult = memberApplicationService.detailMember(new MemberId(memberId));
        log.info("Member[id :{}] is retrieved.", memberId);
        return ApiResponse.success(memberResult);
    }

    @Operation(description = "회원 목록 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "회원 목록 조회 성공")
    })
    @GetMapping(LIST_MEMBER)
    public ApiResponse<List<MemberResult>> list(@RequestParam("memberIds") List<UUID> memberIds) {
        List<MemberId> ids = memberIds.stream()
                .map(MemberId::new)
                .toList();
        List<MemberResult> memberResults = memberApplicationService.listMember(ids);
        return ApiResponse.success(memberResults);
    }

    @Operation(description = "회원가입 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "가입 성공")
    })
    @PutMapping(JOIN_MEMBER)
    public ApiResponse<Boolean> join(@AuthenticationPrincipal UUID memberId,
                                     @RequestBody @Valid JoinRequest joinRequest) {
        JoinCommand joinCommand = JoinCommand.builder()
                .memberId(new MemberId(memberId))
                .nickname(joinRequest.nickname())
                .birthDate(joinRequest.birthDate())
                .gender(joinRequest.gender())
                .deviceToken(joinRequest.deviceToken())
                .build();
        Boolean joinResult = memberApplicationService.join(joinCommand);
        return ApiResponse.success(joinResult);
    }

    @Operation(description = "조건/우선순위 설정 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "조건/우선순위 설정 성공")
    })
    @PostMapping(CONDITION_MEMBER)
    public ApiResponse<Boolean> condition(@AuthenticationPrincipal UUID memberId,
                                     @RequestBody @Valid OnboardingRequest onboardingRequest) {

        Purpose purpose = onboardingRequest.purpose();
        ConditionCommand conditionCommand = null;
        //실거주
        if (purpose == Purpose.LIVING) {
            conditionCommand = ActualLivingConditionCommand.builder()
                    .memberId(new MemberId(memberId))
                    .purpose(onboardingRequest.purpose())
                    .budget(onboardingRequest.budget())
                    .monthIncome(onboardingRequest.monthIncome())
                    .livingPerson(onboardingRequest.livingPerson())
                    .childrenPlan(onboardingRequest.childrenPlan())
                    .commutingArea(onboardingRequest.commutingArea())
                    .traffic(onboardingRequest.traffic())
                    .schoolDistrict(onboardingRequest.schoolDistrict())
                    .infra(onboardingRequest.infra())
                    .environment(onboardingRequest.environment())
                    .build();
        }
        //갭투자
        else if (purpose == Purpose.GAP_INVESTMENT) {
            conditionCommand = GapInvestmentConditionCommand.builder()
                    .memberId(new MemberId(memberId))
                    .purpose(onboardingRequest.purpose())
                    .budget(onboardingRequest.budget())
                    .monthIncome(onboardingRequest.monthIncome())
                    .hopeGap(onboardingRequest.hopeGap())
                    .investmentPlan(onboardingRequest.investmentPlan())
                    .apartmentSquare(onboardingRequest.apartmentSquare())
                    .household(onboardingRequest.household())
                    .houseType(onboardingRequest.houseType())
                    .commutingArea(onboardingRequest.commutingArea())
                    .infra(onboardingRequest.infra())
                    .environment(onboardingRequest.environment())
                    .build();
        }

        //우선순위
        PriorityCommand priorityCommand = PriorityCommand.builder()
                .firstPriority(onboardingRequest.firstPriority())
                .secondPriority(onboardingRequest.secondPriority())
                .thirdPriority(onboardingRequest.thirdPriority())
                .build();

        //관심동네
        InterestDistrictCommand interestDistrictCommand = new InterestDistrictCommand(onboardingRequest.interestDistrict());

        Boolean joinResult = memberApplicationService.condition(conditionCommand, priorityCommand, interestDistrictCommand);
        return ApiResponse.success(joinResult);
    }

    @Operation(description = "회원 탈퇴 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "탈퇴 완료")
    })
    @PutMapping(WITHDRAW_MEMBER)
    public ApiResponse<Boolean> withdraw(@AuthenticationPrincipal UUID memberId,
                                         @RequestBody @Valid WithdrawRequest withdrawRequest) {
        WithdrawCommand withdrawCommand
                = new WithdrawCommand(new MemberId(memberId), withdrawRequest.oAuthProvider(), withdrawRequest.identifier());
        Boolean withdrawResult = memberApplicationService.withdraw(withdrawCommand);
        return ApiResponse.success(withdrawResult);
    }
}
