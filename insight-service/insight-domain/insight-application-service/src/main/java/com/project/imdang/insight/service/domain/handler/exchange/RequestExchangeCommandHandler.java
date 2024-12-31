package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.exception.ExchangeDomainException;
import com.project.imdang.insight.service.domain.exception.SnapshotNotFoundException;
import com.project.imdang.insight.service.domain.mapper.ExchangeRequestDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


@Slf4j
@RequiredArgsConstructor
@Component
public class RequestExchangeCommandHandler {

    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestRepository exchangeRequestRepository;
    private final ExchangeRequestDataMapper exchangeRequestDataMapper;

    private final SnapshotRepository snapshotRepository;

    @Transactional
    public RequestExchangeInsightResponse requestExchange(RequestExchangeInsightCommand requestExchangeInsightCommand) {

        ExchangeRequest exchangeRequest = exchangeRequestDataMapper.requestExchangeInsightCommandToExchangeRequest(requestExchangeInsightCommand);
        checkIsAlreadyExistedExchangeRequest(exchangeRequest);

        // requestedSnapshot
        InsightId requestedInsightId = new InsightId(requestExchangeInsightCommand.getRequestedInsightId());
        Snapshot requestedSnapshot = snapshotRepository.findLatestByInsightId(requestedInsightId)
                .orElseThrow(() -> new SnapshotNotFoundException(requestedInsightId));
//        SnapshotId requestedSnapshotId = requestedSnapshot.getId();

        // requestMemberSnapshot
//        InsightId requestMemberInsightId = null;
//        SnapshotId requestMemberSnapshotId = null;
        Snapshot requestMemberSnapshot = null;
        if (requestExchangeInsightCommand.getRequestMemberInsightId() != null) {
            InsightId requestMemberInsightId = new InsightId(requestExchangeInsightCommand.getRequestMemberInsightId());
            requestMemberSnapshot = snapshotRepository.findLatestByInsightId(requestMemberInsightId)
                    .orElseThrow(() -> new SnapshotNotFoundException(requestedInsightId));
        } else {
            // 쿠폰 사용
            // TODO - 쿠폰 확인
            Assert.notNull(requestExchangeInsightCommand.getMemberCouponId(), "MemberCouponId must not be null!");


        }

        ExchangeRequestCreatedEvent exchangeRequestCreatedEvent
                = exchangeDomainService.requestExchange(exchangeRequest, requestedSnapshot, requestMemberSnapshot);
        ExchangeRequest saved = save(exchangeRequestCreatedEvent.getExchangeRequest());

        // TODO - publish

        return exchangeRequestDataMapper.exchangeRequestToRequestExchangeInsightResponse(saved);
    }

    private ExchangeRequest save(ExchangeRequest exchangeRequest) {
        ExchangeRequest savedExchangeRequest = exchangeRequestRepository.save(exchangeRequest);
        if(savedExchangeRequest == null) {
            String errorMessage = "Could not save ExchangeRequest!";
            log.error(errorMessage);
            throw new ExchangeDomainException(errorMessage);
        }
        log.info("ExchangeRequest[id: {}] is saved.", savedExchangeRequest.getId().getValue());
        return savedExchangeRequest;
    }

    private void checkIsAlreadyExistedExchangeRequest(ExchangeRequest exchangeRequest) {
        // TODO : SnapShot 조회
        exchangeRequestRepository.findByRequestMemberIdAndRequestedInsightId(exchangeRequest.getRequestMemberId(), exchangeRequest.getRequestedInsightId());
    }
}
