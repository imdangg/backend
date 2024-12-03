package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("InsightId[id: {}] is requested to exchange with insightId[id: {}]", requestExchangeInsightCommand.getRequestedInsightId(), requestExchangeInsightCommand.getRequestMemberInsightId());
        RequestExchangeInsightResponse requestExchangeInsightResponse = exchangeApplicationService.requestExchangeInsight(requestExchangeInsightCommand);
        log.info("Exchange is created with id[id : {}]", requestExchangeInsightResponse.getExchangeId());
        return ResponseEntity.ok(requestExchangeInsightResponse);
    }

    /**
     * 인사이트 요청 수락
     */
    @PostMapping("/accept")
    public ResponseEntity<AcceptExchangeRequestResponse> accept(@RequestBody AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        AcceptExchangeRequestResponse acceptExchangeRequestResponse = exchangeApplicationService.acceptExchangeRequest(acceptExchangeRequestCommand);
        log.info("ExchangeId[id: {}] is accepted", acceptExchangeRequestCommand.getExchangeId());
        return ResponseEntity.ok(acceptExchangeRequestResponse);
    }

    /**
     * 인사이트 요청 거절
     */
    @PostMapping("/reject")
    public ResponseEntity<RejectExchangeRequestResponse> reject(@RequestBody RejectExchangeRequestCommand rejectExchangeRequestCommand) {
        RejectExchangeRequestResponse rejectExchangeRequestResponse = exchangeApplicationService.rejectExchangeRequest(rejectExchangeRequestCommand);
        log.info("ExchangeId[id:{}] is rejected", rejectExchangeRequestCommand.getExchangeId());
        return ResponseEntity.ok(rejectExchangeRequestResponse);
    }

    /**
     * 내가 교환 요청한 인사이트 목록
     * String memberId 매개변수 -> Spring Security 적용 이후 변경 예정
     */
    @GetMapping("/reqested-by-me")
    public ResponseEntity<?> listRequestedByMe(String memberId) {
        ListExchangeRequestResponse listExchangeRequestResponse = exchangeApplicationService.listExchangeRequestCreatedByMe(memberId);
        log.info("Member[id : {}] get Exchanges requested by me", memberId);
        return ResponseEntity.ok(listExchangeRequestResponse);
    }

    /**
     * 다른 사람이 나에게 요청한 인사이트 목록
     * String memberId 매개변수 -> Spring Security 적용 이후 변경 예정
     */
    @GetMapping("/requested-by-others")
    public ResponseEntity<?> listRequestedByOther(String memberId) {
        ListExchangeRequestResponse listExchangeRequestResponse = exchangeApplicationService.listExchangeRequestCreatedByOthers(memberId);
        log.info("Member[id : {}] get Exchanges requested by others", memberId);
        return ResponseEntity.ok(listExchangeRequestResponse);
    }
}
