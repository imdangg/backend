package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByMeQuery;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByOthersQuery;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeResponse;
import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequestMapping("/my-exchanges")
@RequiredArgsConstructor
@RestController
public class MyExchangeController {

    private final ExchangeApplicationService exchangeApplicationService;

    // 내가 요청한 내역 (대기, 거절, 완료)
    @GetMapping("/requested-by-me")
    public ResponseEntity<ListExchangeRequestResponse> listRequestedByMe(ListExchangeRequestedByMeQuery listExchangeRequestedByMeQuery) {
        // TODO - Spring Security
        UUID memberId = null;
        ListExchangeRequestResponse listExchangeRequestResponse = exchangeApplicationService.listExchangeRequestedByMe(listExchangeRequestedByMeQuery);
        log.info("List my[id : {}] exchanges requested by me.", memberId);
        return ResponseEntity.ok(listExchangeRequestResponse);
    }

    // 다른 사람이 요청한 내역 (대기, 거절, 완료)
    @GetMapping("/requested-by-others")
    public ResponseEntity<ListExchangeRequestResponse> listRequestedByOthers(ListExchangeRequestedByOthersQuery listExchangeRequestedByOthersQuery) {
        // TODO - Spring Security
        UUID memberId = null;
        ListExchangeRequestResponse listExchangeRequestResponse = exchangeApplicationService.listExchangeRequestedByOthers(listExchangeRequestedByOthersQuery);
        log.info("List my[id : {}] exchanges requested by others.", memberId);
        return ResponseEntity.ok(listExchangeRequestResponse);
    }
}
