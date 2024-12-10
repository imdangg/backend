package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "ExchangeController", description = "인사이트 교환 API ")
public class ExchangeController {

    private final ExchangeApplicationService exchangeApplicationService;

    /**
     * 인사이트 교환 요청
     */
    @Operation(description = "인사이트 교환 요청 API")
    @ApiResponse(responseCode = "200", description = "인사이트 교환 요청 성공",
            content = @Content(schema = @Schema(implementation = RequestExchangeInsightResponse.class)))
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
    @Operation(description = "인사이트 교환 수락 API")
    @ApiResponse(responseCode = "200", description = "인사이트 교환 수락 성공",
            content = @Content(schema = @Schema(implementation = AcceptExchangeRequestResponse.class)))
    @PostMapping("/accept")
    public ResponseEntity<AcceptExchangeRequestResponse> accept(@RequestBody AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        AcceptExchangeRequestResponse acceptExchangeRequestResponse = exchangeApplicationService.acceptExchangeRequest(acceptExchangeRequestCommand);
        log.info("ExchangeRequest[id: {}] is accepted.", acceptExchangeRequestCommand.getExchangeRequestId());
        return ResponseEntity.ok(acceptExchangeRequestResponse);
    }

    /**
     * 인사이트 요청 거절
     */
    @Operation(description = "인사이트 교환 거절 API")
    @ApiResponse(responseCode = "200", description = "인사이트 교환 거절 성공",
            content = @Content(schema = @Schema(implementation = RejectExchangeRequestResponse.class)))
    @PostMapping("/reject")
    public ResponseEntity<RejectExchangeRequestResponse> reject(@RequestBody RejectExchangeRequestCommand rejectExchangeRequestCommand) {
        RejectExchangeRequestResponse rejectExchangeRequestResponse = exchangeApplicationService.rejectExchangeRequest(rejectExchangeRequestCommand);
        log.info("ExchangeRequest[id:{}] is rejected.", rejectExchangeRequestCommand.getExchangeRequestId());
        return ResponseEntity.ok(rejectExchangeRequestResponse);
    }
}
