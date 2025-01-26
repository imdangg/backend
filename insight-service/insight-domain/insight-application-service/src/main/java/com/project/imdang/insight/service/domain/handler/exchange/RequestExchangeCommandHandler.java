package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.message.ExchangeRequestCreatedRequestMessage;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.event.EventPublisher;
import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.exception.InsightApplicationServiceException;
import com.project.imdang.insight.service.domain.exception.SnapshotNotFoundException;
import com.project.imdang.insight.service.domain.handler.ExchangeRequestCreatedRequestMessagePublisherImpl;
import com.project.imdang.insight.service.domain.handler.ExchangeRequestHelper;
import com.project.imdang.insight.service.domain.mapper.ExchangeRequestDataMapper;
import com.project.imdang.insight.service.domain.ports.output.lookup.InsightMemberLookup;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.project.imdang.domain.exception.ErrorCode.MEMBER_15ACCUSED;
import static com.project.imdang.domain.exception.ErrorCode.MEMBER_5ACCUSED;
import static com.project.imdang.domain.exception.ErrorCode.MEMBER_NOT_EXIST;


@Slf4j
@RequiredArgsConstructor
@Component
public class RequestExchangeCommandHandler {

    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestRepository exchangeRequestRepository;
    private final ExchangeRequestDataMapper exchangeRequestDataMapper;
    private final ExchangeRequestHelper exchangeRequestHelper;

    private final SnapshotRepository snapshotRepository;

    private final InsightMemberLookup insightMemberLookup;

    private final ExchangeRequestCreatedRequestMessagePublisherImpl exchangeRequestCreatedRequestMessagePublisher;
    private final EventPublisher eventPublisher;

    @Transactional
    public RequestExchangeInsightResponse requestExchange(RequestExchangeInsightCommand requestExchangeInsightCommand) {

        // 5회, 15회 누적 사용자 - 교환 신청/수락/거절 버튼 클릭 시 팝업 노출
        MemberId requestMemberId = new MemberId(requestExchangeInsightCommand.getRequestMemberId());
        checkMember(requestMemberId);

        ExchangeRequest exchangeRequest = exchangeRequestDataMapper.requestExchangeInsightCommandToExchangeRequest(requestExchangeInsightCommand);
        checkIsAlreadyExistedExchangeRequest(exchangeRequest);

        InsightId requestedInsightId = new InsightId(requestExchangeInsightCommand.getRequestedInsightId());
        Snapshot requestedSnapshot = snapshotRepository.findLatestByInsightId(requestedInsightId)
                .orElseThrow(() -> new SnapshotNotFoundException(requestedInsightId));

        ExchangeRequestCreatedEvent exchangeRequestCreatedEvent;
        if (requestExchangeInsightCommand.getRequestMemberInsightId() != null) {

            InsightId requestMemberInsightId = new InsightId(requestExchangeInsightCommand.getRequestMemberInsightId());
            Snapshot requestMemberSnapshot = snapshotRepository.findLatestByInsightId(requestMemberInsightId)
                    .orElseThrow(() -> new SnapshotNotFoundException(requestedInsightId));

            exchangeRequestCreatedEvent = exchangeDomainService.requestExchange(exchangeRequest, requestedSnapshot, requestMemberSnapshot);

        } else {
            // 쿠폰 사용
            Assert.notNull(requestExchangeInsightCommand.getMemberCouponId(), "MemberCouponId must not be null!");
            MemberCouponId memberCouponId = new MemberCouponId(requestExchangeInsightCommand.getMemberCouponId());

            // publish
            exchangeRequestCreatedRequestMessagePublisher.publish(
                    new ExchangeRequestCreatedRequestMessage(memberCouponId.getValue()));
            exchangeRequestCreatedEvent = exchangeDomainService.requestExchangeWithCoupon(exchangeRequest, requestedSnapshot, memberCouponId);
        }

        ExchangeRequest saved = exchangeRequestHelper.save(exchangeRequestCreatedEvent.getExchangeRequest());
        eventPublisher.publish(exchangeRequestCreatedEvent);

        return exchangeRequestDataMapper.exchangeRequestToRequestExchangeInsightResponse(saved);
    }

    private void checkMember(MemberId requestMemberId) {
        MemberInfo memberInfo = insightMemberLookup.lookupByMemberId(requestMemberId)
                .orElseThrow(() -> new InsightApplicationServiceException(MEMBER_NOT_EXIST));

        if (memberInfo.accusedCount() == 5) {
            throw new InsightApplicationServiceException(MEMBER_5ACCUSED);
        } else if (memberInfo.accusedCount() == 15) {
            throw new InsightApplicationServiceException(MEMBER_15ACCUSED);
        }
    }

    private void checkIsAlreadyExistedExchangeRequest(ExchangeRequest exchangeRequest) {
        // TODO : SnapShot 조회
        exchangeRequestRepository.findByRequestMemberIdAndRequestedInsightId(exchangeRequest.getRequestMemberId(), exchangeRequest.getRequestedInsightId());
    }
}
