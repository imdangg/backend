package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.DistrictResponse;
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
public class ListMyInsightDistrictCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;

    @Transactional(readOnly = true)
    public List<DistrictResponse> listMyInsightDistrict(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        List<Object[]> districts = memberSnapshotRepository.findAllDistinctDistrictByMemberId(memberId);

        // TODO - 쿼리 개선
        return districts.stream()
                .map(_district -> {
                    String siDo = (String) _district[0];
                    String siGunGu = (String) _district[1];
                    String eupMyeonDong = (String) _district[2];
                    District district = new District(siDo, siGunGu, eupMyeonDong);
                    Long[] result = memberSnapshotRepository.countAllByMemberIdAndDistrict(memberId, district);

                    Long apartmentComplexCount = result[0];
                    Long insightCount = result[1];
                    return DistrictResponse.builder()
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
