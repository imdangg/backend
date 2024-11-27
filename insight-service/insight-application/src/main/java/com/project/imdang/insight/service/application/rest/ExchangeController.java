package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/exchanges")
@RequiredArgsConstructor
@RestController
public class ExchangeController {
/*
    private final ExchangeApplicationService exchangeApplicationService;

    @PostMapping
    public ResponseEntity<RequestExchangeInsightResponse> request(@RequestBody RequestExchangeInsightCommand requestExchangeInsightCommand) {
        log.info("InsightId {} is requested to exchange with insightId {}", requestExchangeInsightCommand.getRequestedInsightId(), requestExchangeInsightCommand.getRequestMemberInsightId());
        RequestExchangeInsightResponse requestExchangeInsightResponse = exchangeApplicationService.requestExchangeInsight(requestExchangeInsightCommand);
        // TODO : exchange vs exchangeRequest
        log.info("Exchange is created with id : {}", requestExchangeInsightResponse.getExchangeId());
        return ResponseEntity.ok(requestExchangeInsightResponse);
    }

    @PostMapping
    public ResponseEntity<AcceptExchangeRequestResponse> accept(@RequestBody AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        AcceptExchangeRequestResponse acceptExchangeRequestResponse = exchangeApplicationService.acceptExchangeRequest(acceptExchangeRequestCommand);
        log.info("ExchangeId {} is accepted", acceptExchangeRequestCommand.getExchangeId());
        return ResponseEntity.ok(acceptExchangeRequestResponse);
    }

    @PostMapping
    public ResponseEntity<RejectExchangeRequestResponse> reject(@RequestBody RejectExchangeRequestCommand rejectExchangeRequestCommand) {
        RejectExchangeRequestResponse rejectExchangeRequestResponse = exchangeApplicationService.rejectExchangeRequest(rejectExchangeRequestCommand);
        log.info("ExchangeId {} is rejected", rejectExchangeRequestCommand.getExchangeId());
        return ResponseEntity.ok(rejectExchangeRequestResponse);
    }*/
}
