package com.project.imdang.insight.domain.dto.insight.list;

import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.InsightId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InsightSimpleResult {
    private InsightId insightId;
    private Long recommendedCount;
    private Address address;
    private String title;
}
