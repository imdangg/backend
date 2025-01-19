package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.MyInsightResponse;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.District;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class CountMyInsightByDistrictCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;

    @Transactional(readOnly = true)
    public MyInsightResponse countMyInsightByDistrict(UUID _memberId, District district) {
        MemberId memberId = new MemberId(_memberId);
        List<ApartmentComplex> apartmentComplexes = memberSnapshotRepository.findAllDistinctApartmentComplexByMemberIdAndDistrict(memberId, district);
        Long[] counts = memberSnapshotRepository.countAllByMemberIdAndDistrict(memberId, district);
//        Long apartmentComplexCount = counts[0];
        Long insightCount = counts[1];
        return MyInsightResponse.builder()
                .apartmentComplexes(apartmentComplexes)
                .insightCount(insightCount)
                .build();
    }
}
