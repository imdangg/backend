package com.project.imdang.insight.service.domain.dto.insight.list;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ListInsightResponse {
    // 리스트 limit 5
        /*
        "data": [
            "insightId1": {
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
            },
            "insightId2": {
                ...
            }
        ]
         */

    // TODO - ASK : 일주일 간 추천순 ?
    // 일주일 간 추천순
    // 리스트 limit 10
        /*
        "data": [
            "insightId1": {
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
            },
            "insightId2": {
                ...
            }
        ]
         */
}
