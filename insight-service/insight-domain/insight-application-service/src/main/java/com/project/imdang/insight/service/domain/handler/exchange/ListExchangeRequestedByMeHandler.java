package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByMeQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.lookup.InsightMemberLookup;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import com.project.imdang.insight.service.domain.valueobject.MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListExchangeRequestedByMeHandler {

    private final ExchangeRequestRepository exchangeRequestRepository;
    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    private final InsightMemberLookup insightMemberLookup;
    
    @Transactional(readOnly = true)
    public Page<InsightResponse> list(ListExchangeRequestedByMeQuery listExchangeRequestedByMeQuery) {

        UUID requestMemberId = listExchangeRequestedByMeQuery.getRequestMemberId();
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listExchangeRequestedByMeQuery.getPageNumber(), listExchangeRequestedByMeQuery.getPageSize(),
                listExchangeRequestedByMeQuery.getDirection(), listExchangeRequestedByMeQuery.getProperties());
        ExchangeRequestStatus exchangeRequestStatus = listExchangeRequestedByMeQuery.getExchangeRequestStatus();
        Page<ExchangeRequest> paged
                = exchangeRequestRepository.findAllByRequestMemberIdAndExchangeRequestStatus(new MemberId(requestMemberId), exchangeRequestStatus, pageRequest);

        List<InsightId> requestedInsightIds = paged.getContent().stream()
                .map(ExchangeRequest::getRequestedInsightId)
                .collect(Collectors.toList());
        List<Insight> requestedInsights = insightRepository.findAllByIds(requestedInsightIds);
        List<InsightResponse> insightResponses = getInsightResponses(requestedInsights);
        return new PageImpl<>(insightResponses, paged.getPageable(), paged.getTotalElements());
    }

    private List<InsightResponse> getInsightResponses(List<Insight> insights) {
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(insights);
        return insights.stream().map(insight -> {
            String memberNickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, memberNickname);
        }).toList();
    }

    private Map<MemberId, String> getMemberNicknameMap(List<Insight> insights) {
        List<MemberId> memberIds = insights.stream()
                .map(Insight::getMemberId)
                .toList();
        return insightMemberLookup.lookupByMemberIds(memberIds).stream()
                .collect(Collectors.toMap(MemberInfo::memberId, MemberInfo::nickname));
    }
}
