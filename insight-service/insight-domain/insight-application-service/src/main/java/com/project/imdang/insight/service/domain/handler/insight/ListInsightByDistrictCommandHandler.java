package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByDistrictQuery;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.lookup.InsightMemberLookup;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.valueobject.District;
import com.project.imdang.insight.service.domain.valueobject.MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListInsightByDistrictCommandHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    private final InsightMemberLookup insightMemberLookup;

    @Transactional(readOnly = true)
    public Page<InsightResponse> listInsightByDistrict(ListInsightByDistrictQuery listInsightByDistrictQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByDistrictQuery.getPageNumber(), listInsightByDistrictQuery.getPageSize(), listInsightByDistrictQuery.getDirection(), listInsightByDistrictQuery.getProperties());

        District district = District.builder()
                .siDo(listInsightByDistrictQuery.getSiDo())
                .siGunGu(listInsightByDistrictQuery.getSiGunGu())
                .eupMyeonDong(listInsightByDistrictQuery.getEupMyeonDong())
                .build();
        Page<Insight> paged = insightRepository.findAllByDistrict(district, pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());
        return paged.map(insight -> {
                    String memberNickname = memberNicknameMap.get(insight.getMemberId());
                    return insightDataMapper.insightToInsightResponse(insight, memberNickname);
                });
    }

    private Map<MemberId, String> getMemberNicknameMap(List<Insight> insights) {
        List<MemberId> memberIds = insights.stream()
                .map(Insight::getMemberId)
                .toList();
        return insightMemberLookup.lookupByMemberIds(memberIds).stream()
                .collect(Collectors.toMap(MemberInfo::memberId, MemberInfo::nickname));
    }
}
