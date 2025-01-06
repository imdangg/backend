package com.project.imdang.insight.service.domain.dto.insight.list;

import com.project.imdang.insight.service.domain.valueobject.Address;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "인사이트ID")
    private UUID insightId;
    @Schema(description = "추천 수")
    private Integer recommendedCount;
    @Schema(description = "주소")
    private Address address;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "메인 이미지")
    private String mainImage;
    @Schema(description = "사용자ID")
    private UUID memberId;
//    private MemberResponse member;
}
