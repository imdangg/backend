package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.MemberInfoResponse;
import com.project.imdang.member.service.domain.dto.DetailMyPageQuery;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.ports.input.service.MemberApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<MemberInfoResponse> info(@RequestParam UUID memberId) {
        MemberInfoResponse memberInfoResponse = memberApplicationService.detailMember(memberId);
        log.info("Member[id :{}] is retrived", memberId);
        return ResponseEntity.ok(memberInfoResponse);
    }
}
