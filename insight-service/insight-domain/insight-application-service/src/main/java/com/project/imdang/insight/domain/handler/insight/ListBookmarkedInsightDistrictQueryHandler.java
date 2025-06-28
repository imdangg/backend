package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.list.DistrictOfBookmarkedInsightResult;
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
public class ListBookmarkedInsightDistrictQueryHandler {

    private final InsightRepository insightRepository;

    @Transactional(readOnly = true)
    public List<DistrictOfBookmarkedInsightResult> listBookmarkedInsightDistrict(MemberId memberId) {
        // 내가 작성한 인사이트 + 추천한 인사이트 지역 목록 조회
        List<Object[]> districts = insightRepository.findAllDistrictByMemberId(memberId);

        // TODO - 쿼리 개선
        return districts.stream()
                .map(_district -> {
                    String siDo = (String) _district[0];
                    String siGunGu = (String) _district[1];
                    String eupMyeonDong = (String) _district[2];
                    District district = District.builder()
                            .siDo(siDo)
                            .siGunGu(siGunGu)
                            .eupMyeonDong(eupMyeonDong)
                            .build();

                    // 지역 별 단지 수 및 인사이트 개수 조회
                    Long[] result = insightRepository.countAllByMemberIdAndDistrict(memberId, district);
                    Long apartmentComplexCount = result[0];
                    Long insightCount = result[1];

                    return DistrictOfBookmarkedInsightResult.builder()
                            .siDo(siDo)
                            .siGunGu(siGunGu)
                            .eupMyeonDong(eupMyeonDong)
                            .apartmentComplexCount(apartmentComplexCount)
                            .insightCount(insightCount)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
