package com.project.imdang.insight.service.domain.dto.insight.list;

import com.project.imdang.insight.service.domain.valueobject.Address;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InsightResponse {

    private UUID insightId;
    private Integer recommendedCount;
    private Address address;
    private String title;
    private String mainImage;
    private UUID memberId;
//    private MemberResponse member;
}
