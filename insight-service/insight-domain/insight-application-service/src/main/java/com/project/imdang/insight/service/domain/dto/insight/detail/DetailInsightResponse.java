package com.project.imdang.insight.service.domain.dto.insight.detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DetailInsightResponse {
/*
"data": {
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
    private UUID insightId;
}
