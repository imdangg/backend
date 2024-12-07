package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByOthersQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListExchangeRequestedByOthersHandler {

    private final ExchangeRequestRepository exchangeRequestRepository;
    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    @Transactional(readOnly = true)
    public Page<InsightResponse> list(ListExchangeRequestedByOthersQuery listExchangeRequestedByOthersQuery) {

        UUID requestedMemberId = listExchangeRequestedByOthersQuery.getRequestedMemberId();
        Sort sort = Sort.by(Sort.Direction.valueOf(listExchangeRequestedByOthersQuery.getDirection()), listExchangeRequestedByOthersQuery.getProperties());
        PageRequest pageRequest = PageRequest.of(listExchangeRequestedByOthersQuery.getPageNumber(), listExchangeRequestedByOthersQuery.getPageSize(), sort);

        ExchangeRequestStatus exchangeRequestStatus = listExchangeRequestedByOthersQuery.getExchangeRequestStatus();
        Page<ExchangeRequest> paged
                = exchangeRequestRepository.findAllByRequestedMemberIdAndExchangeRequestStatus(new MemberId(requestedMemberId), exchangeRequestStatus, pageRequest);
        // TODO - 테스트
        List<InsightId> insightIds = paged.getContent().stream().map(ExchangeRequest::getRequestedInsightId).collect(Collectors.toList());
        List<InsightResponse> insightResponses = insightRepository.findAllByIds(insightIds).stream()
                .map(insightDataMapper::insightToInsightResponse)
                .toList();
        return new PageImpl<>(insightResponses, paged.getPageable(), paged.getTotalElements());
    }
}
