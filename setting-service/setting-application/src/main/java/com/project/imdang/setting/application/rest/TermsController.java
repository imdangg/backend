package com.project.imdang.setting.application.rest;

import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.TermsId;
import com.project.imdang.setting.domain.dto.AgreeTermsCommand;
import com.project.imdang.setting.domain.dto.TermsResult;
import com.project.imdang.setting.domain.ports.input.service.TermsApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.project.imdang.common.application.constant.RequestPath.AGREE_TERMS;
import static com.project.imdang.common.application.constant.RequestPath.LIST_TERMS;

@Slf4j
@Tag(name = "TermsController", description = "약관 API")
@RequiredArgsConstructor
@RestController
public class TermsController {

    private final TermsApplicationService termsApplicationService;

    @Operation(description = "약관 목록 조회 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "약관 목록 조회 성공")
    })
    @GetMapping(LIST_TERMS)
    public ApiResponse<List<TermsResult>> list() {
        List<TermsResult> termsResults = termsApplicationService.listTerms();
        return ApiResponse.success(termsResults);
    }

    @Operation(description = "약관 동의 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "약관 동의 성공")
    })
    @PostMapping(AGREE_TERMS)
    public ApiResponse<Void> agree(@AuthenticationPrincipal UUID memberId,
                                   @RequestParam("termsIds") List<Long> termsIds
//                                   @RequestBody @Valid AgreeTermsRequest agreeTermsRequest
    ) {
        Set<TermsId> ids = termsIds.stream()
                .map(TermsId::new)
                .collect(Collectors.toSet());
        AgreeTermsCommand agreeTermsCommand = AgreeTermsCommand.builder()
                .memberId(new MemberId(memberId))
                .termsIds(ids)
                .build();
        termsApplicationService.agreeTerms(agreeTermsCommand);
        return ApiResponse.success(null);
    }
}
