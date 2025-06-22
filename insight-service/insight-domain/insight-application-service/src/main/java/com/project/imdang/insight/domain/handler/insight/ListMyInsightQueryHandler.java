package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.utils.PagingUtils;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.list.InsightResult;
import com.project.imdang.insight.domain.dto.insight.list.ListMyInsightCreatedByMeQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListMyInsightQuery;
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

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightQueryHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;
    private final MemberDataResolver memberResolver;

    @Transactional(readOnly = true)
    public Page<InsightResult> listMyInsight(ListMyInsightQuery listMyInsightQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listMyInsightQuery.getPageNumber(), listMyInsightQuery.getPageSize(), listMyInsightQuery.getDirection(), listMyInsightQuery.getProperties());
        MemberId memberId = listMyInsightQuery.getMemberId();
        Boolean onlyMine = listMyInsightQuery.getOnlyMine();

        Page<Insight> paged;
        //단지별 보기 선택 시
        if (listMyInsightQuery.getApartmentComplexName() != null) {
            // 단지
            ApartmentComplex apartmentComplex = new ApartmentComplex(listMyInsightQuery.getApartmentComplexName());

            //내 인사이트만 보기 활성화 시
            if (Boolean.TRUE.equals(onlyMine)) {
                paged = insightRepository.findAllByMemberIdAndApartmentComplexAndOnlyMine(memberId, apartmentComplex, pageRequest);
            }
            //내 인사이트만 보기 비활성화 시
            else {
                paged = insightRepository.findAllByMemberIdAndApartmentComplex(memberId, apartmentComplex, pageRequest);
            }
        }
        // 전체 선택 시 (단지별 보기가 아닌 경우)
        else {
            //내 인사이트만 보기 활성화 시
            if (Boolean.TRUE.equals(onlyMine)) {
                paged = insightRepository.findAllByMemberIdAndDistrictAndOnlyMine(memberId, listMyInsightQuery.getDistrict(), pageRequest);
            }
            // 내 인사이트만 보기 비활성화 시
            else {
                paged = insightRepository.findAllByMemberIdAndDistrict(memberId, listMyInsightQuery.getDistrict(), pageRequest);
            }
        }

        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());

        return paged.map(insight -> {
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
