package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.list.MyDistrictResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightDistrictQueryHandler {

    @Transactional(readOnly = true)
    public List<MyDistrictResult> listMyInsightDistrict(MemberId memberId) {
        List<Object[]> districts = new ArrayList<>();

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
                    Long[] result = new Long[10];

                    Long apartmentComplexCount = result[0];
                    Long insightCount = result[1];
                    return MyDistrictResult.builder()
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
