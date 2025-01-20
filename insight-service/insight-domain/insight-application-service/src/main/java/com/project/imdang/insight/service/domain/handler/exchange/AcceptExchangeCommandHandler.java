package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.handler.ExchangeRequestHelper;
import com.project.imdang.insight.service.domain.handler.MemberSnapshotHelper;
import com.project.imdang.insight.service.domain.mapper.ExchangeRequestDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class AcceptExchangeCommandHandler {

    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestHelper exchangeRequestHelper;
    private final ExchangeRequestDataMapper exchangeRequestDataMapper;

    private final MemberSnapshotHelper memberSnapshotHelper;

    @Transactional
    public AcceptExchangeRequestResponse acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        ExchangeRequestId exchangeRequestId = new ExchangeRequestId(acceptExchangeRequestCommand.getExchangeRequestId());
        ExchangeRequest exchangeRequest = exchangeRequestHelper.get(exchangeRequestId);

        // validation check
        if (!exchangeRequest.getRequestedMemberId().getValue()
                .equals(acceptExchangeRequestCommand.getRequestedMemberId())) {
            throw new RuntimeException();
        }

        if (exchangeRequest.getMemberCouponId() != null) {
            // 쿠폰 사용 처리 : 쿠폰 사용한 ExchangeRequest 수락 시, MemberCoupon 상태 'USED' 처리
            // 'USED' 처리 완료 - process
            // 'USED' 처리 실패 - rollback
            MemberCouponId memberCouponId = exchangeRequest.getMemberCouponId();
//            exchangeRequestAcceptedRequestMessagePublisher.publish(
//                    new ExchangeRequestAcceptedRequestMessage(exchangeRequest.getId().getValue()));

        } else {

            ExchangeRequestAcceptedEvent exchangeRequestAcceptedEvent = exchangeDomainService.acceptExchangeRequest(exchangeRequest);
            log.info("ExchangeRequest[id: {}] is accepted.", exchangeRequest.getId().getValue());
            ExchangeRequest saved = exchangeRequestHelper.save(exchangeRequest);

            // memberSnapshot에 insert
            MemberSnapshot requestMemberSnapshot = MemberSnapshot.builder()
                    .memberId(exchangeRequest.getRequestMemberId())
                    .snapshotId(exchangeRequest.getRequestedSnapshotId())
                    .insightId(exchangeRequest.getRequestedInsightId())
                    // TODO - CHECK
                    .createdAt(exchangeRequestAcceptedEvent.getCreatedAt())
                    .build();
            memberSnapshotHelper.save(requestMemberSnapshot);

            MemberSnapshot requestedMemberSnapshot = MemberSnapshot.builder()
                    .memberId(exchangeRequest.getRequestedMemberId())
                    .snapshotId(exchangeRequest.getRequestMemberSnapshotId())
                    .insightId(exchangeRequest.getRequestMemberInsightId())
                    // TODO - CHECK
                    .createdAt(exchangeRequestAcceptedEvent.getCreatedAt())
                    .build();
            memberSnapshotHelper.save(requestedMemberSnapshot);
        }
        return null;
//        return exchangeRequestDataMapper.exchangeRequestToAcceptExchangeRequestResponse(saved);
    }
}
