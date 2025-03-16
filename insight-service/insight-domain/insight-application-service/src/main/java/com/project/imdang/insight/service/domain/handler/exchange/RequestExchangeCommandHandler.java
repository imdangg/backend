package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.exception.ErrorCode;
import com.project.imdang.domain.message.ExchangeRequestCreatedRequestMessage;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.event.EventProcessor;
import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.event.ExchangeRequestByCouponCreatedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.exception.InsightApplicationServiceException;
import com.project.imdang.insight.service.domain.exception.SnapshotNotFoundException;
import com.project.imdang.insight.service.domain.handler.ExchangeRequestHelper;
import com.project.imdang.insight.service.domain.handler.InsightHelper;
import com.project.imdang.insight.service.domain.mapper.ExchangeRequestDataMapper;
import com.project.imdang.insight.service.domain.ports.output.lookup.InsightMemberLookup;
import com.project.imdang.insight.service.domain.ports.output.publisher.ExchangeRequestCreatedRequestMessagePublisher;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
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
    private final InsightHelper insightHelper;

    private final SnapshotRepository snapshotRepository;

    private final InsightMemberLookup insightMemberLookup;

    private final ExchangeRequestCreatedRequestMessagePublisher exchangeRequestCreatedRequestMessagePublisher;
    private final EventProcessor eventProcessor;

    @Transactional
    public RequestExchangeInsightResponse requestExchange(RequestExchangeInsightCommand requestExchangeInsightCommand) {

        // 5회, 15회 누적 사용자 - 교환 신청/수락/거절 버튼 클릭 시 팝업 노출
        MemberId requestMemberId = new MemberId(requestExchangeInsightCommand.getRequestMemberId());
        checkMember(requestMemberId);

        InsightId requestedInsightId = new InsightId(requestExchangeInsightCommand.getRequestedInsightId());
        Insight requestedInsight = checkInsight(requestedInsightId);

        // 동일한 요청(수락/대기중) 내역 존재 시, 요청 불가
        exchangeRequestRepository.findByRequestMemberIdAndRequestedInsightId(requestMemberId, requestedInsightId).stream()
                .filter(e -> !e.getStatus().equals(ExchangeRequestStatus.REJECTED))
                .findAny()
                .ifPresent((e) -> {
                    throw new InsightApplicationServiceException(ErrorCode.ALREADY_EXCHANGE_REQUESTED);
                });

        ExchangeRequest saved;
        if (requestExchangeInsightCommand.getRequestMemberInsightId() != null) {

            // 상호 교환 불가
            InsightId requestMemberInsightId = new InsightId(requestExchangeInsightCommand.getRequestMemberInsightId());
            checkInsight(requestMemberInsightId);

            exchangeRequestRepository.findByRequestMemberInsightIdAndRequestedInsightId(requestedInsightId, requestMemberInsightId).stream()
                    .filter(e -> !e.getStatus().equals(ExchangeRequestStatus.REJECTED))
                    .findAny()
                    .ifPresent((e) -> {
                        throw new InsightApplicationServiceException(ErrorCode.ALREADY_EXCHANGE_REQUESTED);
                    });

            // 쿠폰으로 요청된 이력이 있는 경우 - 요청 불가
            exchangeRequestRepository.findByRequestMemberIdAndRequestedInsightIdAndMemberCouponIsNotNull(requestedInsight.getMemberId(), requestMemberInsightId).stream()
                    .filter(e -> !e.getStatus().equals(ExchangeRequestStatus.REJECTED))
                    .findAny()
                    .ifPresent((e) -> {
                        throw new InsightApplicationServiceException(ErrorCode.ALREADY_EXCHANGE_REQUESTED);
                    });

            ExchangeRequest exchangeRequest = exchangeRequestDataMapper.requestExchangeInsightCommandToExchangeRequest(requestExchangeInsightCommand);
            Snapshot requestedSnapshot = snapshotRepository.findLatestByInsightId(requestedInsightId)
                    .orElseThrow(() -> new SnapshotNotFoundException(requestedInsightId));
            Snapshot requestMemberSnapshot = snapshotRepository.findLatestByInsightId(requestMemberInsightId)
                    .orElseThrow(() -> new SnapshotNotFoundException(requestedInsightId));

            ExchangeRequestCreatedEvent exchangeRequestCreatedEvent = exchangeDomainService.requestExchange(exchangeRequest, requestedSnapshot, requestMemberSnapshot);
            saved = exchangeRequestHelper.save(exchangeRequestCreatedEvent.getExchangeRequest());
            eventProcessor.process(exchangeRequestCreatedEvent);
        } else {
            // 쿠폰 사용
            Assert.notNull(requestExchangeInsightCommand.getMemberCouponId(), "MemberCouponId must not be null!");
            MemberCouponId memberCouponId = new MemberCouponId(requestExchangeInsightCommand.getMemberCouponId());

            // 인사이트로 요청된 이력이 있는 경우 - 요청 불가
            exchangeRequestRepository.findByRequestedMemberIdAndRequestMemberInsightId(requestMemberId, requestedInsightId).stream()
                    .filter(e -> !e.getStatus().equals(ExchangeRequestStatus.REJECTED))
                    .findAny()
                    .ifPresent((e) -> {
                        throw new InsightApplicationServiceException(ErrorCode.ALREADY_EXCHANGE_REQUESTED);
                    });

            // publish
            exchangeRequestCreatedRequestMessagePublisher.publish(
                    new ExchangeRequestCreatedRequestMessage(memberCouponId.getValue()));

            ExchangeRequest exchangeRequest = exchangeRequestDataMapper.requestExchangeInsightCommandToExchangeRequest(requestExchangeInsightCommand);
            Snapshot requestedSnapshot = snapshotRepository.findLatestByInsightId(requestedInsightId)
                    .orElseThrow(() -> new SnapshotNotFoundException(requestedInsightId));
            ExchangeRequestByCouponCreatedEvent exchangeRequestByCouponCreatedEvent = exchangeDomainService.requestExchangeWithCoupon(exchangeRequest, requestedSnapshot, memberCouponId);
            saved = exchangeRequestHelper.save(exchangeRequestByCouponCreatedEvent.getExchangeRequest());
            eventProcessor.process(exchangeRequestByCouponCreatedEvent);
        }

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

    private Insight checkInsight(InsightId insightId) {
        return insightHelper.get(insightId);
    }
}
