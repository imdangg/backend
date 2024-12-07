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

    private final ExchangeApplicationService exchangeApplicationService;

    /**
     * 인사이트 교환 요청
     */
    @PostMapping("/request")
    public ResponseEntity<RequestExchangeInsightResponse> request(@RequestBody RequestExchangeInsightCommand requestExchangeInsightCommand) {
        log.info("Insight[id: {}] is requested to exchange with insight[id: {}].", requestExchangeInsightCommand.getRequestedInsightId(), requestExchangeInsightCommand.getRequestMemberInsightId());
        RequestExchangeInsightResponse requestExchangeInsightResponse = exchangeApplicationService.requestExchangeInsight(requestExchangeInsightCommand);
        log.info("ExchangeRequest[id : {}] is created.", requestExchangeInsightResponse.getExchangeRequestId());
        return ResponseEntity.ok(requestExchangeInsightResponse);
    }

    /**
     * 인사이트 요청 수락
     */
    @PostMapping("/accept")
    public ResponseEntity<AcceptExchangeRequestResponse> accept(@RequestBody AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        AcceptExchangeRequestResponse acceptExchangeRequestResponse = exchangeApplicationService.acceptExchangeRequest(acceptExchangeRequestCommand);
        log.info("ExchangeRequest[id: {}] is accepted.", acceptExchangeRequestCommand.getExchangeRequestId());
        return ResponseEntity.ok(acceptExchangeRequestResponse);
    }

    /**
     * 인사이트 요청 거절
     */
    @PostMapping("/reject")
    public ResponseEntity<RejectExchangeRequestResponse> reject(@RequestBody RejectExchangeRequestCommand rejectExchangeRequestCommand) {
        RejectExchangeRequestResponse rejectExchangeRequestResponse = exchangeApplicationService.rejectExchangeRequest(rejectExchangeRequestCommand);
        log.info("ExchangeRequest[id:{}] is rejected.", rejectExchangeRequestCommand.getExchangeRequestId());
        return ResponseEntity.ok(rejectExchangeRequestResponse);
    }
}
