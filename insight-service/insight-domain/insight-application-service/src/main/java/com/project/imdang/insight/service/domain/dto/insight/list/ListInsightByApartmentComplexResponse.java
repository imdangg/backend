package com.project.imdang.insight.service.domain.dto.insight.list;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ListInsightByApartmentComplexResponse {
    // 내가 다녀온 단지(group by) + 타인이 작성한 인사이트 리스트 limit 3
        /*
        "data": [
            "apartmentComplex1": {
                insights: [
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
            },
            "apartmentComplex2": {
                ...
            }
        ]
         */
}
