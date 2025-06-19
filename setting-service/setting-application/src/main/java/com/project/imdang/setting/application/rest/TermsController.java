package com.project.imdang.setting.application.rest;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.TermsId;
import com.project.imdang.setting.application.dto.AgreeTermsRequest;
import com.project.imdang.setting.domain.dto.AgreeTermsCommand;
import com.project.imdang.setting.domain.dto.TermsResult;
import com.project.imdang.setting.domain.ports.input.service.TermsApplicationService;
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
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "TermsController", description = "약관 API")
@RequestMapping("/terms")
@RequiredArgsConstructor
@RestController
public class TermsController {

    private final TermsApplicationService termsApplicationService;

    @Operation(description = "약관 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "약관 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = TermsResult.class)))
    @GetMapping
    public ResponseEntity<List<TermsResult>> list() {
        List<TermsResult> termsResults = termsApplicationService.listTerms();
        return ResponseEntity.ok(termsResults);
    }

    @Operation(description = "약관 동의 API")
    @ApiResponse(responseCode = "200", description = "약관 동의 성공")
    @PostMapping("/agree")
    public ResponseEntity<Void> agree(@AuthenticationPrincipal UUID memberId,
                                      @RequestBody @Valid AgreeTermsRequest agreeTermsRequest) {
        Set<TermsId> termsIds = agreeTermsRequest.getTermsIds().stream()
                .map(TermsId::new)
                .collect(Collectors.toSet());
        AgreeTermsCommand agreeTermsCommand = AgreeTermsCommand.builder()
                .memberId(new MemberId(memberId))
                .termsIds(termsIds)
                .build();
        termsApplicationService.agreeTerms(agreeTermsCommand);
        return ResponseEntity.ok().build();
    }
}
