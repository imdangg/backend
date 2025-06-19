package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.utils.PagingUtils;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.list.InsightResult;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightByApartmentComplexQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightByDateQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightByDistrictQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import com.project.imdang.member.domain.client.MemberData;
import com.project.imdang.member.domain.client.MemberDataResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Component
public class ListInsightQueryHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    private final MemberDataResolver memberResolver;

    public Page<InsightResult> list(ListInsightQuery listInsightQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightQuery.getPageNumber(), listInsightQuery.getPageSize(), listInsightQuery.getDirection(), listInsightQuery.getProperties());

        Page<Insight> paged = insightRepository.findAll(pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());
        return paged.map(insight -> {
            String memberNickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, memberNickname);
        });
    }

    public Page<InsightResult> listByDate(ListInsightByDateQuery listInsightByDateQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByDateQuery.getPageNumber(), listInsightByDateQuery.getPageSize(), listInsightByDateQuery.getDirection(), listInsightByDateQuery.getProperties());

        Page<Insight> paged = insightRepository.findAllByDate(listInsightByDateQuery.getDate(), pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());
        return paged.map(insight -> {
            String memberNickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, memberNickname);
        });
    }

    public Page<InsightResult> listByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByApartmentComplexQuery.getPageNumber(), listInsightByApartmentComplexQuery.getPageSize(), listInsightByApartmentComplexQuery.getDirection(), listInsightByApartmentComplexQuery.getProperties());
        ApartmentComplex apartmentComplex = ApartmentComplex.builder()
                .name(listInsightByApartmentComplexQuery.getApartmentComplexName())
                .build();

        Page<Insight> paged = insightRepository.findAllByApartmentComplex(apartmentComplex, pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());
        return paged.map(insight -> {
            String memberNickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, memberNickname);
        });
    }

    public Page<InsightResult> listByDistrict(ListInsightByDistrictQuery listInsightByDistrictQuery) {
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

    private List<InsightResult> getInsightResponses(List<Insight> insights) {
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
        return memberResolver.resolve(memberIds).stream()
                .collect(Collectors.toMap(memberData -> new MemberId(memberData.getMemberId()), MemberData::getNickname));
    }
}
