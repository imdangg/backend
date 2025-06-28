package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.list.ApartmentComplexOfBookmarkedInsightResult;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListBookmarkedInsightApartmentComplexByDistrictQueryHandler {

    private final InsightRepository insightRepository;

    @Transactional(readOnly = true)
    public List<ApartmentComplexOfBookmarkedInsightResult> listBookmarkedInsightApartmentComplexByDistrict(MemberId memberId, District district) {
        //단지별 인사이트 개수 조회
        List<Object[]> results = insightRepository.findAllDistinctApartmentComplexAndInsightCountByMemberIdAndDistrict(memberId, district);
        return results.stream()
                .map(result -> ApartmentComplexOfBookmarkedInsightResult.builder()
                        .apartmentComplexName((String) result[0])
                        .insightCount((Long) result[1])
                        .build())
                .collect(Collectors.toList());
    }
}
