package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.DetailMemberResponse;
import com.project.imdang.member.service.domain.dto.DetailMyPageQuery;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.dto.oauth.apple.AppleWithdrawCommand;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleWithdrawCommand;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoWithdrawCommand;
import com.project.imdang.member.service.domain.ports.input.service.MemberApplicationService;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "MemberController", description = "마이페이지 API")
@RequestMapping("members")
public class MemberController {
    private final MemberApplicationService memberApplicationService;

    @Operation(description = "마이페이지 API")
    @ApiResponse(responseCode = "200", description = "마이페이지 조회 성공",
        content = @Content(schema = @Schema(implementation = DetailMyPageResponse.class)))
    @GetMapping("/detail")
    public ResponseEntity<DetailMyPageResponse> detail(@AuthenticationPrincipal UUID memberId) {
        DetailMyPageQuery detailMyPageQuery = new DetailMyPageQuery(memberId);
        DetailMyPageResponse detailMyPageResponse = memberApplicationService.detailMyPage(detailMyPageQuery);
        log.info("Member[id : {}] mypage is viewed", memberId);
        return ResponseEntity.ok(detailMyPageResponse);
    }

    //TODO - CHECK : 전체 회원 정보 가져오기 or 필요한 정보만 가져오기
    @GetMapping
    public ResponseEntity<DetailMemberResponse> info(@RequestParam UUID memberId) {
        DetailMemberResponse detailMemberResponse = memberApplicationService.detailMember(memberId);
        log.info("Member[id :{}] is retrived", memberId);
        return ResponseEntity.ok(detailMemberResponse);
    }

    /**
     * 로그아웃
     */
    @Operation(description = "로그아웃 API")
    @ApiResponse(responseCode = "200", description = "로그아웃 완료")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UUID memberId) {
        memberApplicationService.logout(memberId);
        return ResponseEntity.ok().build();
    }

    //회원 탈퇴
    @Operation(description = "카카오 회원 탈퇴 API")
    @ApiResponse(responseCode = "200", description = "탈퇴 완료")
    @PostMapping("/withdrawal/kakao")
    public ResponseEntity<Void> withdraw(@AuthenticationPrincipal UUID memberId, @RequestBody @Valid KakaoWithdrawCommand kakaoWithdrawCommand) {
        memberApplicationService.withdraw(memberId, kakaoWithdrawCommand);
        return ResponseEntity.ok().build();
    }

    //회원 탈퇴
    @Operation(description = "구글 회원 탈퇴 API")
    @ApiResponse(responseCode = "200", description = "탈퇴 완료")
    @PostMapping("/withdrawal/google")
    public ResponseEntity<Void> withdraw(@AuthenticationPrincipal UUID memberId,@RequestBody @Valid GoogleWithdrawCommand googleWithdrawCommand) {
        memberApplicationService.withdraw(memberId, googleWithdrawCommand);
        return ResponseEntity.ok().build();
    }

    //회원 탈퇴
    @Operation(description = "애플 회원 탈퇴 API")
    @ApiResponse(responseCode = "200", description = "탈퇴 완료")
    @PostMapping("/withdrawal/apple")
    public ResponseEntity<Void> withdraw(@AuthenticationPrincipal UUID memberId, @RequestBody @Valid AppleWithdrawCommand appleWithdrawCommand) {
        memberApplicationService.withdraw(memberId, appleWithdrawCommand);
        return ResponseEntity.ok().build();
    }
}
