package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRequestDataMapper {

    public ExchangeRequest requestExchangeInsightCommandToExchangeRequest(RequestExchangeInsightCommand requestExchangeInsightCommand) {
       return ExchangeRequest.builder()
               .requestedInsightId(new InsightId(requestExchangeInsightCommand.getRequestedInsightId()))
               .requestMemberId(new MemberId(requestExchangeInsightCommand.getRequestMemberId()))
               .requestMemberInsightId(
                       requestExchangeInsightCommand.getRequestMemberInsightId() != null ?
                       new InsightId(requestExchangeInsightCommand.getRequestMemberInsightId()) : null)
               // 쿠폰으로 요청 시
               .memberCouponId(
                       requestExchangeInsightCommand.getMemberCouponId() != null ?
                       new MemberCouponId(requestExchangeInsightCommand.getMemberCouponId()) : null)
               .build();
    }

    public RequestExchangeInsightResponse exchangeRequestToRequestExchangeInsightResponse(ExchangeRequest exchangeRequest) {
        return RequestExchangeInsightResponse.builder()
                .exchangeRequestId(exchangeRequest.getId().getValue())
                .build();
    }

    public AcceptExchangeRequestResponse exchangeRequestToAcceptExchangeRequestResponse(ExchangeRequest exchangeRequest) {
        return AcceptExchangeRequestResponse.builder()
                .exchangeRequestId(exchangeRequest.getId().getValue())
                .build();
    }

    public RejectExchangeRequestResponse exchangeRequestToRejectExchangeRequestResponse(ExchangeRequest exchangeRequest) {
        return RejectExchangeRequestResponse.builder()
                .exchangeRequestId(exchangeRequest.getId().getValue())
                .build();
    }
}
