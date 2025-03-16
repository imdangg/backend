package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.message.ExchangeRequestAcceptedCountRequestMessage;
import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.event.EventProcessor;
import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.handler.ExchangeRequestHelper;
import com.project.imdang.insight.service.domain.handler.MemberSnapshotHelper;
import com.project.imdang.insight.service.domain.mapper.ExchangeRequestDataMapper;
import com.project.imdang.insight.service.domain.ports.output.publisher.ExchangeRequestAcceptedCountMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class AcceptExchangeRequestCommandHandler {

    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestHelper exchangeRequestHelper;
    private final ExchangeRequestDataMapper exchangeRequestDataMapper;

    private final MemberSnapshotHelper memberSnapshotHelper;
    private final ExchangeRequestAcceptedCountMessagePublisher exchangeRequestAcceptedCountMessagePublisher;
    private final EventProcessor eventProcessor;

    @Transactional
    public AcceptExchangeRequestResponse acceptExchangeRequest(
            AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        ExchangeRequestId exchangeRequestId = new ExchangeRequestId(
                acceptExchangeRequestCommand.getExchangeRequestId());
        ExchangeRequest exchangeRequest = exchangeRequestHelper.get(exchangeRequestId);

        // validation check
        if (!exchangeRequest.getRequestedMemberId().getValue()
                .equals(acceptExchangeRequestCommand.getRequestedMemberId())) {
            throw new IllegalArgumentException();
        }
        /*
         * if (exchangeRequest.getMemberCouponId() != null) {
         * MemberCouponId memberCouponId = exchangeRequest.getMemberCouponId();
         * exchangeRequestAcceptedRequestMessagePublisher.publish(
         * new ExchangeRequestAcceptedRequestMessage(memberCouponId.getValue()));
         * }
         */

        ExchangeRequestAcceptedEvent exchangeRequestAcceptedEvent = exchangeDomainService
                .acceptExchangeRequest(exchangeRequest);
        // publish event
        ExchangeRequestAcceptedCountRequestMessage event = new ExchangeRequestAcceptedCountRequestMessage(
                exchangeRequest.getRequestMemberId().getValue());
        exchangeRequestAcceptedCountMessagePublisher.publish(event);
        eventProcessor.process(exchangeRequestAcceptedEvent);

        log.info("ExchangeRequest[id: {}] is accepted.", exchangeRequest.getId().getValue());
        ExchangeRequest saved = exchangeRequestHelper.save(exchangeRequestAcceptedEvent.getExchangeRequest());

        MemberSnapshot requestMemberSnapshot = MemberSnapshot.builder()
                .memberId(exchangeRequest.getRequestMemberId())
                .snapshotId(exchangeRequest.getRequestedSnapshotId())
                .insightId(exchangeRequest.getRequestedInsightId())
                .exchangeRequestId(saved.getId())
                // TODO - CHECK
                .createdAt(exchangeRequestAcceptedEvent.getCreatedAt())
                .build();
        memberSnapshotHelper.save(requestMemberSnapshot);

        if (exchangeRequest.getRequestMemberSnapshotId() != null) {
            MemberSnapshot requestedMemberSnapshot = MemberSnapshot.builder()
                    .memberId(exchangeRequest.getRequestedMemberId())
                    .snapshotId(exchangeRequest.getRequestMemberSnapshotId())
                    .insightId(exchangeRequest.getRequestMemberInsightId())
                    .exchangeRequestId(saved.getId())
                    // TODO - CHECK
                    .createdAt(exchangeRequestAcceptedEvent.getCreatedAt())
                    .build();
            memberSnapshotHelper.save(requestedMemberSnapshot);
        }

        return exchangeRequestDataMapper.exchangeRequestToAcceptExchangeRequestResponse(saved);
    }
}
