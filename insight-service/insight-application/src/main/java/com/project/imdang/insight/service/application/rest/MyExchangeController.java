package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByMeQuery;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByOthersQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequestMapping("/my-exchanges")
@RequiredArgsConstructor
@RestController
@Tag(name = "MyExchangeController", description = "인사이트 교환소 API ")
public class MyExchangeController {

    private final ExchangeApplicationService exchangeApplicationService;

    // 내가 요청한 내역 (대기, 거절, 완료)
    @Operation(description = "내가 요청한 인사이트 교환 목록 API")
    @ApiResponse(responseCode = "200", description = "내가 요청한 인사이트 교환 목록 조회 성공")
    @GetMapping("/requested-by-me")
    public ResponseEntity<Page<InsightResponse>> listRequestedByMe(@AuthenticationPrincipal UUID memberId,
                                                                   @ModelAttribute ListExchangeRequestedByMeQuery listExchangeRequestedByMeQuery) {
        listExchangeRequestedByMeQuery.setRequestMemberId(memberId);
        Page<InsightResponse> paged = exchangeApplicationService.listExchangeRequestedByMe(listExchangeRequestedByMeQuery);
        log.info("List my[id : {}] exchanges requested by me.", memberId);
        return ResponseEntity.ok(paged);
    }

    // 다른 사람이 요청한 내역 (대기, 거절, 완료)
    @Operation(description = "다른 사람이 요청한 인사이트 교환 목록 API")
    @ApiResponse(responseCode = "200", description = "다른 사람이 요청한 인사이트 교환 목록 조회 성공")
    @GetMapping("/requested-by-others")
    public ResponseEntity<Page<InsightResponse>> listRequestedByOthers(@AuthenticationPrincipal UUID memberId,
                                                                       @ModelAttribute ListExchangeRequestedByOthersQuery listExchangeRequestedByOthersQuery) {
        listExchangeRequestedByOthersQuery.setRequestedMemberId(memberId);
        Page<InsightResponse> paged = exchangeApplicationService.listExchangeRequestedByOthers(listExchangeRequestedByOthersQuery);
        log.info("List my[id : {}] exchanges requested by others.", memberId);
        return ResponseEntity.ok(paged);
    }
}
