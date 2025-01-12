package com.project.imdang.setting.service.application.rest;

import com.project.imdang.setting.service.domain.dto.AgreeTermsCommand;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.dto.TermsResponse;
import com.project.imdang.setting.service.domain.ports.input.service.TermsApplicationService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@Tag(name = "TermsController", description = "약관 API")
@RequestMapping("/terms")
@RequiredArgsConstructor
@RestController
public class TermsController {

    private final TermsApplicationService termsApplicationService;

    @Operation(description = "약관 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "약관 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = TermsResponse.class)))
    @GetMapping
    public ResponseEntity<List<TermsResponse>> list() {
        List<TermsResponse> list = termsApplicationService.listTerms();
        return ResponseEntity.ok(list);
    }

    @Operation(description = "약관 동의 API")
    @ApiResponse(responseCode = "200", description = "약관 동의 성공")
    @PostMapping("/agree")
    public ResponseEntity<Void> agree(@AuthenticationPrincipal UUID memberId, @RequestBody @Valid AgreeTermsCommand agreeTermsCommand) {
        agreeTermsCommand.setMemberId(memberId);
        termsApplicationService.agreeTerms(agreeTermsCommand);
        return ResponseEntity.ok().build();
    }
}
