package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.ApartmentComplexResponse;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.District;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListApartmentComplexByDistrictCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;

    @Transactional(readOnly = true)
    public List<ApartmentComplexResponse> listApartmentComplexByDistrict(UUID _memberId, District district) {
        MemberId memberId = new MemberId(_memberId);
        List<Object[]> results = memberSnapshotRepository.findAllDistinctApartmentComplexAndInsightCountByMemberIdAndDistrict(memberId, district);
        return results.stream()
                .map(result -> ApartmentComplexResponse.builder()
                        .apartmentComplexName((String) result[0])
                        .insightCount((Long) result[1])
                        .build())
                .collect(Collectors.toList());
    }
}
