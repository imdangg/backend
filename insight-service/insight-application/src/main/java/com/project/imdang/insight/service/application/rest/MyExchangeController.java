package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByMeQuery;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByOthersQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;

import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/my-exchanges")
@RequiredArgsConstructor
@RestController
public class MyExchangeController {

    private final ExchangeApplicationService exchangeApplicationService;

    // 내가 요청한 내역 (대기, 거절, 완료)
    @GetMapping("/requested-by-me")
    public ResponseEntity<List<InsightResponse>> listRequestedByMe(ListExchangeRequestedByMeQuery listExchangeRequestedByMeQuery) {
        // TODO - Spring Security
        UUID memberId = null;
        List<InsightResponse> insightResponses = exchangeApplicationService.listExchangeRequestedByMe(listExchangeRequestedByMeQuery);
        log.info("List my[id : {}] exchanges requested by me.", memberId);
        return ResponseEntity.ok(insightResponses);
    }

    // 다른 사람이 요청한 내역 (대기, 거절, 완료)
    @GetMapping("/requested-by-others")
    public ResponseEntity<List<InsightResponse>> listRequestedByOthers(ListExchangeRequestedByOthersQuery listExchangeRequestedByOthersQuery) {
        // TODO - Spring Security
        UUID memberId = null;
        List<InsightResponse> insightResponses = exchangeApplicationService.listExchangeRequestedByOthers(listExchangeRequestedByOthersQuery);
        log.info("List my[id : {}] exchanges requested by others.", memberId);
        return ResponseEntity.ok(insightResponses);
    }
}
