package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.fetch.FetchMemberResponse;
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
import com.project.imdang.insight.service.domain.handler.ExchangeRequestCreatedRequestMessagePublisher;
import com.project.imdang.insight.service.domain.handler.ExchangeRequestHelper;
import com.project.imdang.insight.service.domain.mapper.ExchangeRequestDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.project.imdang.domain.exception.ErrorCode.MEMBER_15ACCUSED;
import static com.project.imdang.domain.exception.ErrorCode.MEMBER_5ACCUSED;


@Slf4j
@RequiredArgsConstructor
@Component
public class RequestExchangeCommandHandler {

    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestRepository exchangeRequestRepository;
    private final ExchangeRequestDataMapper exchangeRequestDataMapper;
    private final ExchangeRequestHelper exchangeRequestHelper;

    private final SnapshotRepository snapshotRepository;
//    private final MemberFetcher memberFetcher;

    private final ExchangeRequestCreatedRequestMessagePublisher exchangeRequestCreatedRequestMessagePublisher;
    private final EventPublisher eventPublisher;

    @Transactional
    public RequestExchangeInsightResponse requestExchange(RequestExchangeInsightCommand requestExchangeInsightCommand) {

        // 5회, 15회 누적 사용자 - 교환 신청/수락/거절 버튼 클릭 시 팝업 노출
        MemberId requestMemberId = new MemberId(requestExchangeInsightCommand.getRequestMemberId());
        checkMember(requestMemberId);

        ExchangeRequestCreatedEvent exchangeRequestCreatedEvent;
        ExchangeRequest exchangeRequest = exchangeRequestDataMapper.requestExchangeInsightCommandToExchangeRequest(requestExchangeInsightCommand);
        checkIsAlreadyExistedExchangeRequest(exchangeRequest);

        InsightId requestedInsightId = new InsightId(requestExchangeInsightCommand.getRequestedInsightId());
        Snapshot requestedSnapshot = snapshotRepository.findLatestByInsightId(requestedInsightId)
                .orElseThrow(() -> new SnapshotNotFoundException(requestedInsightId));

        ExchangeRequest saved;
        if (requestExchangeInsightCommand.getRequestMemberInsightId() != null) {

            InsightId requestMemberInsightId = new InsightId(requestExchangeInsightCommand.getRequestMemberInsightId());
            Snapshot requestMemberSnapshot = snapshotRepository.findLatestByInsightId(requestMemberInsightId)
                    .orElseThrow(() -> new SnapshotNotFoundException(requestedInsightId));

            exchangeRequestCreatedEvent = exchangeDomainService.requestExchange(exchangeRequest, requestedSnapshot, requestMemberSnapshot);
            saved = exchangeRequestHelper.save(exchangeRequestCreatedEvent.getExchangeRequest());
            eventPublisher.publish(exchangeRequestCreatedEvent);

        } else {
            // 쿠폰 사용
            // TODO - 쿠폰 확인
            Assert.notNull(requestExchangeInsightCommand.getMemberCouponId(), "MemberCouponId must not be null!");
            MemberCouponId memberCouponId = new MemberCouponId(requestExchangeInsightCommand.getMemberCouponId());
            ExchangeRequest requestExchangeWithCoupon = exchangeDomainService.requestExchangeWithCoupon(exchangeRequest, requestedSnapshot, memberCouponId);
            saved = exchangeRequestHelper.save(requestExchangeWithCoupon);
            // TODO - CHECK : publish 위치
            exchangeRequestCreatedRequestMessagePublisher.publish(
                    new ExchangeRequestCreatedRequestMessage(saved.getId().getValue()));
        }

        // TODO - CHECK : 쿠폰 업데이트 실패 시, rollback 메소드에서 데이터 삭제하면?
        return exchangeRequestDataMapper.exchangeRequestToRequestExchangeInsightResponse(saved);
    }

    private void checkMember(MemberId requestMemberId) {
        FetchMemberResponse requestMember = null;
//                memberFetcher.fetchByMemberId(requestMemberId);
        if (requestMember.getAccusedCount() == 5) {
            throw new InsightApplicationServiceException(MEMBER_5ACCUSED);
        } else if (requestMember.getAccusedCount() == 15) {
            throw new InsightApplicationServiceException(MEMBER_15ACCUSED);
        }
    }

    private void checkIsAlreadyExistedExchangeRequest(ExchangeRequest exchangeRequest) {
        // TODO : SnapShot 조회
        exchangeRequestRepository.findByRequestMemberIdAndRequestedInsightId(exchangeRequest.getRequestMemberId(), exchangeRequest.getRequestedInsightId());
    }
}
