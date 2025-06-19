package com.project.imdang.member.application.rest;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.application.dto.member.OAuthWithdrawRequest;
import com.project.imdang.member.domain.dto.member.MemberResult;
import com.project.imdang.member.domain.dto.member.MyPageInfoResult;
import com.project.imdang.member.domain.dto.member.apple.AppleWithdrawCommand;
import com.project.imdang.member.domain.dto.member.google.GoogleWithdrawCommand;
import com.project.imdang.member.domain.dto.member.kakao.KakaoWithdrawCommand;
import com.project.imdang.member.domain.ports.input.service.MemberApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "MemberController", description = "마이페이지 API")
@RequestMapping("/members")
public class MemberController {
    
    private final MemberApplicationService memberApplicationService;

    @Operation(description = "마이페이지 API")
    @ApiResponse(responseCode = "200", description = "마이페이지 조회 성공",
        content = @Content(schema = @Schema(implementation = MyPageInfoResult.class)))
    @GetMapping("/detail")
    public ResponseEntity<MyPageInfoResult> detail(@AuthenticationPrincipal UUID memberId) {
        MyPageInfoResult myPageInfoResult = memberApplicationService.detailMyPage(new MemberId(memberId));
        log.info("MyPage of Member[id : {}] is viewed.", memberId);
        return ResponseEntity.ok(myPageInfoResult);
    }

    @GetMapping("/info")
    public ResponseEntity<MemberResult> info(@RequestParam UUID memberId) {
        MemberResult memberResult = memberApplicationService.detailMember(new MemberId(memberId));
        log.info("Member[id :{}] is retrieved.", memberId);
        return ResponseEntity.ok(memberResult);
    }

    @GetMapping
    public ResponseEntity<List<MemberResult>> list(@RequestParam List<UUID> memberIds) {
        List<MemberId> ids = memberIds.stream()
                .map(MemberId::new)
                .toList();
        List<MemberResult> memberResults = memberApplicationService.listMember(ids);
        return ResponseEntity.ok(memberResults);
    }

    /**
     * 로그아웃
     */
    @Operation(description = "로그아웃 API")
    @ApiResponse(responseCode = "200", description = "로그아웃 완료")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UUID memberId) {
        memberApplicationService.logout(new MemberId(memberId));
        return ResponseEntity.ok().build();
    }
    
    // TODO - API 통합
/*
    @Operation(description = "회원 탈퇴 API")
    @ApiResponse(responseCode = "200", description = "탈퇴 완료")
    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@AuthenticationPrincipal UUID memberId, @RequestBody @Valid OAuthWithdrawCommand_ oAuthWithdrawCommand) {
        memberApplicationService.withdraw(memberId, oAuthWithdrawCommand);
        return ResponseEntity.ok().build();
    }*/
    //회원 탈퇴
    @Operation(description = "카카오 회원 탈퇴 API")
    @ApiResponse(responseCode = "200", description = "탈퇴 완료")
    @PostMapping("/withdrawal/kakao")
    public ResponseEntity<Void> withdrawKakao(@AuthenticationPrincipal UUID memberId,
                                              @RequestBody @Valid OAuthWithdrawRequest oAuthWithdrawRequest) {
        KakaoWithdrawCommand kakaoWithdrawCommand = KakaoWithdrawCommand.builder()
                .memberId(new MemberId(memberId))
                .token(oAuthWithdrawRequest.token())
                .build();
        memberApplicationService.withdraw(kakaoWithdrawCommand);
        return ResponseEntity.ok().build();
    }

    //회원 탈퇴
    @Operation(description = "구글 회원 탈퇴 API")
    @ApiResponse(responseCode = "200", description = "탈퇴 완료")
    @PostMapping("/withdrawal/google")
    public ResponseEntity<Void> withdrawGoogle(@AuthenticationPrincipal UUID memberId,
                                               @RequestBody @Valid OAuthWithdrawRequest oAuthWithdrawRequest) {
        GoogleWithdrawCommand googleWithdrawCommand = GoogleWithdrawCommand.builder()
                .memberId(new MemberId(memberId))
                .token(oAuthWithdrawRequest.token())
                .build();
        memberApplicationService.withdraw(googleWithdrawCommand);
        return ResponseEntity.ok().build();
    }

    //회원 탈퇴
    @Operation(description = "애플 회원 탈퇴 API")
    @ApiResponse(responseCode = "200", description = "탈퇴 완료")
    @PostMapping("/withdrawal/apple")
    public ResponseEntity<Void> withdrawApple(@AuthenticationPrincipal UUID memberId,
                                              @RequestBody @Valid OAuthWithdrawRequest oAuthWithdrawRequest) {
        AppleWithdrawCommand appleWithdrawCommand = AppleWithdrawCommand.builder()
                .memberId(new MemberId(memberId))
                .token(oAuthWithdrawRequest.token())
                .build();
        memberApplicationService.withdraw(appleWithdrawCommand);
        return ResponseEntity.ok().build();
    }
}
