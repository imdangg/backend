package com.project.imdang.insight.domain.dto.insight.list;

import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.InsightId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InsightResult {
    private InsightId insightId;
    private Integer recommendedCount;
    private Address address;
    private String title;
    private List<String> images;        // image URL 리스트
    private List<Integer> types;        // 이미지 타입 리스트
    private List<Integer> sortNums;     // 정렬 순서 리스트
    private String memberNickname;
    private ZonedDateTime createdAt;
}
