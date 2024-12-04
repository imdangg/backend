package com.project.imdang.insight.service.domain.dto.insight.preview;

import com.project.imdang.insight.service.domain.dto.insight.list.MemberResponse;
import com.project.imdang.insight.service.domain.valueobject.Address;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PreviewInsightResponse {
// TODO : vs InsightResponse
    private UUID insightId;
    private Integer recommendedCount;
    private Address address;
    private String title;
    private String mainImage;

    // TODO - CHECK
    private UUID memberId;
//    private MemberResponse member;
    private ZonedDateTime createdAt;
    private Integer score;
/*
"data": {
    // ID
    "insightId": "insight-id-1",
    // 추천 수
    "recommendedCount": 24,
    // 위치
    "address": "강남구 신논현동",
    // 제목
    "title": "초역세권 대단지 아파트 후기",
    // 인사이트 대표 사진
    "image": "image.jpg",
    // 작성자
    "member": {
        // 작성자명
        "nickname": "글쓴이",
        // 프로필 사진
        "image": "profile.jpg"
    }
    // 작성일자
    "createdAt": "",
    // 완성도
    "score"
}
 */
}
