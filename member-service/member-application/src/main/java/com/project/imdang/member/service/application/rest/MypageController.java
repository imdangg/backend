package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.DetailMyPageQuery;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.ports.input.service.MemberApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "MypageController", description = "마이페이지 API")
@RequestMapping("/my-page")
public class MypageController {
    private final MemberApplicationService memberApplicationService;

    @GetMapping
    public ResponseEntity<DetailMyPageResponse> detail(@AuthenticationPrincipal UUID memberId) {
        DetailMyPageQuery detailMyPageQuery = new DetailMyPageQuery(memberId);
        DetailMyPageResponse detailMyPageResponse = memberApplicationService.detailMyPage(detailMyPageQuery);
        log.info("Member[id : {}] mypage is viewed", memberId);
        return ResponseEntity.ok(detailMyPageResponse);
    }
}
