package com.project.imdang.insight.service.domain.dto.insight.list;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistrictResponse {
    private String siDo; // 시/도 (예: 서울특별시)
    private String siGunGu; // 시/군/구 (예: 종로구)
    private String eupMyeonDong; // 읍/면/동 (예: 효제동)

    private Long apartmentComplexCount;
    private Long insightCount;
}
