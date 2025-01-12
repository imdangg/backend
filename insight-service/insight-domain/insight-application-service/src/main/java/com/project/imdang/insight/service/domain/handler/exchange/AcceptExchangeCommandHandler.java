package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.exception.ExchangeDomainException;
import com.project.imdang.insight.service.domain.exception.ExchangeRequestNotFoundException;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.mapper.ExchangeRequestDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class AcceptExchangeCommandHandler {

    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestRepository exchangeRequestRepository;
    private final MemberSnapshotRepository memberSnapshotRepository;
    private final ExchangeRequestDataMapper exchangeRequestDataMapper;


    @Transactional
    public AcceptExchangeRequestResponse acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        ExchangeRequestId exchangeRequestId = new ExchangeRequestId(acceptExchangeRequestCommand.getExchangeRequestId());
        ExchangeRequest exchangeRequest = checkExchangeRequest(exchangeRequestId);

        // check
        if (!exchangeRequest.getRequestedMemberId().getValue().equals(acceptExchangeRequestCommand.getRequestedMemberId())) {
            throw new RuntimeException();
        }
        ExchangeRequestAcceptedEvent exchangeRequestAcceptedEvent = exchangeDomainService.acceptExchangeRequest(exchangeRequest);
        log.info("ExchangeRequest[id: {}] is accepted.", exchangeRequest.getId().getValue());
        ExchangeRequest saved = save(exchangeRequest);

        // memberSnapshot에 insert
        MemberSnapshot requestMemberSnapshot = MemberSnapshot.builder()
                .memberId(exchangeRequest.getRequestMemberId())
                .snapshotId(exchangeRequest.getRequestedSnapshotId())
                .insightId(exchangeRequest.getRequestedInsightId())
                // TODO - CHECK
                .createdAt(exchangeRequestAcceptedEvent.getCreatedAt())
                .build();
        saveMemberSnapshot(requestMemberSnapshot);

        if (exchangeRequest.getMemberCouponId() != null) {
            // TODO : 쿠폰 사용 처리

        } else {
            MemberSnapshot requestedMemberSnapshot = MemberSnapshot.builder()
                    .memberId(exchangeRequest.getRequestedMemberId())
                    .snapshotId(exchangeRequest.getRequestMemberSnapshotId())
                    .insightId(exchangeRequest.getRequestMemberInsightId())
                    // TODO - CHECK
                    .createdAt(exchangeRequestAcceptedEvent.getCreatedAt())
                    .build();
            saveMemberSnapshot(requestedMemberSnapshot);
        }

        // TODO : publish


        return exchangeRequestDataMapper.exchangeRequestToAcceptExchangeRequestResponse(saved);
    }

    private ExchangeRequest checkExchangeRequest(ExchangeRequestId exchangeRequestId) {
        return exchangeRequestRepository.findById(exchangeRequestId)
                .orElseThrow(() -> new ExchangeRequestNotFoundException(exchangeRequestId));
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

    private void saveMemberSnapshot(MemberSnapshot memberSnapshot) {
        MemberSnapshot saved = memberSnapshotRepository.save(memberSnapshot);
        if (saved == null) {
            String errorMessage = "Could not save memberSnapshot!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("memberSnapshot[id: {}] is saved.", saved.getId().getValue());
    }
}
