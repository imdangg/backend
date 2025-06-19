package com.project.imdang.insight.domain.dto.insight.list;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyApartmentComplexResult {
    private String apartmentComplexName;
    private Long insightCount;
}
