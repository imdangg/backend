package com.project.imdang.insight.service.domain.dto.insight.list;

import com.project.imdang.insight.service.domain.valueobject.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class InsightResponse {

    private UUID insightId;
    private Integer recommendedCount;
    private Address address;
    private String title;
    private String mainImage;
    private MemberResponse member;
}
