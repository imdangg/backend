package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.utils.PagingUtils;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.list.*;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import com.project.imdang.member.domain.client.MemberData;
import com.project.imdang.member.domain.client.MemberDataResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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

        //추천수 top 10 인사이트 목록 조회
        Page<Insight> paged = insightRepository.findAll(pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());

        return getInsightResult(paged, memberNicknameMap);
    }

    public Page<InsightResult> listByDate(ListInsightByDateQuery listInsightByDateQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByDateQuery.getPageNumber(), listInsightByDateQuery.getPageSize(), listInsightByDateQuery.getDirection(), listInsightByDateQuery.getProperties());

        //오늘 기준 작성된 인사이트 목록 조회
        Page<Insight> paged = insightRepository.findAllByDate(listInsightByDateQuery.getDate(), pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());

        return getInsightResult(paged, memberNicknameMap);
    }

    public Page<InsightResult> listByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByApartmentComplexQuery.getPageNumber(), listInsightByApartmentComplexQuery.getPageSize(), listInsightByApartmentComplexQuery.getDirection(), listInsightByApartmentComplexQuery.getProperties());

        //아파트 단지 정보
        ApartmentComplex apartmentComplex = ApartmentComplex.builder()
                .name(listInsightByApartmentComplexQuery.getApartmentComplexName())
                .build();

        //아파트 단지별 인사이트 목록 조회
        Page<Insight> paged = insightRepository.findAllByApartmentComplex(apartmentComplex, pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());

        return getInsightResult(paged, memberNicknameMap);
    }

    public Page<InsightResult> listByDistrict(ListInsightByDistrictQuery listInsightByDistrictQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByDistrictQuery.getPageNumber(), listInsightByDistrictQuery.getPageSize(), listInsightByDistrictQuery.getDirection(), listInsightByDistrictQuery.getProperties());

        //지역 정보
        District district = District.builder()
                .siDo(listInsightByDistrictQuery.getSiDo())
                .siGunGu(listInsightByDistrictQuery.getSiGunGu())
                .eupMyeonDong(listInsightByDistrictQuery.getEupMyeonDong())
                .build();

        //지역별 인사이트 목록 조회
        Page<Insight> paged = insightRepository.findAllByDistrict(district, pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());

        return getInsightResult(paged, memberNicknameMap);
    }

    //인사이트 목록 DTO 반환
    private Page<InsightResult> getInsightResult(Page<Insight> insightPage, Map<MemberId, String> memberNicknameMap) {
        return insightPage.map(insight -> {
                    String memberNickname = memberNicknameMap.get(insight.getMemberId());
                    return insightDataMapper.insightToInsightResponse(insight, memberNickname);
        });
    }

    //인사이트별 작성자 닉네임 조립
    private Map<MemberId, String> getMemberNicknameMap(List<Insight> insights) {
        List<MemberId> memberIds = insights.stream()
                .map(Insight::getMemberId)
                .toList();
        return memberResolver.resolve(memberIds).stream()
                .collect(Collectors.toMap(memberData -> new MemberId(memberData.getMemberId()), MemberData::getNickname));
    }
}
