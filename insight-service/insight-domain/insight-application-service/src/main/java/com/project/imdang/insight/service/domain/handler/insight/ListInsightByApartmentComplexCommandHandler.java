package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByApartmentComplexQuery;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.lookup.InsightMemberLookup;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
public class ListInsightByApartmentComplexCommandHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    private final InsightMemberLookup insightMemberLookup;

    @Transactional(readOnly = true)
    public Page<InsightResponse> listInsightByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByApartmentComplexQuery.getPageNumber(), listInsightByApartmentComplexQuery.getPageSize(), listInsightByApartmentComplexQuery.getDirection(), listInsightByApartmentComplexQuery.getProperties());
        ApartmentComplex apartmentComplex = ApartmentComplex.builder()
                .name(listInsightByApartmentComplexQuery.getApartmentComplexName())
                .build();

        Page<Insight> paged = insightRepository.findAllByApartmentComplex(apartmentComplex, pageRequest);
        Map<UUID, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());
        return paged.map(insight -> {
                    String memberNickname = memberNicknameMap.get(insight.getMemberId().getValue());
                    return insightDataMapper.insightToInsightResponse(insight, memberNickname);
                });
    }

    private Map<UUID, String> getMemberNicknameMap(List<Insight> insights) {
        List<MemberId> memberIds = insights.stream()
                .map(Insight::getMemberId)
                .toList();
        return insightMemberLookup.lookupByMemberIds(memberIds).stream()
                .collect(Collectors.toMap(MemberInfo::memberId, MemberInfo::nickname));
    }
}
