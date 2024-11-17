package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.insight.detail.InsightDetailQuery;
import com.project.imdang.insight.service.domain.dto.insight.detail.InsightDetailResponse;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequestMapping("/insights")
@RequiredArgsConstructor
@RestController
public class InsightController {

    private InsightApplicationService insightApplicationService;

    @GetMapping("")
    public ResponseEntity<?> listByAddress() {
        // 지역으로 찾기
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
        return null;
    }

    @GetMapping("")
    public ResponseEntity<?> listGroupByMyVisitedApartmentComplex() {
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
        // "다녀온 단지가 없어요. 단지에 임장을 다녀오고 인사이트를 남겨보세요."
        return ResponseEntity.ok(null);
    }

    // /insights - 최신순
    @GetMapping("")
    public ResponseEntity<?> listByCreatedAtDesc() {
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
        return ResponseEntity.ok(null);
    }

    // /insights - 인기순(추천수 순)
    @GetMapping("")
    public ResponseEntity<?> listByRecommendedCountDesc() {
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
        return ResponseEntity.ok(null);
    }

    // 상세, 프리뷰
    @GetMapping("/{insightId}")
    public ResponseEntity<InsightDetailResponse> detail(@PathVariable UUID insightId) {
        InsightDetailQuery insightDetailQuery = new InsightDetailQuery(insightId);
        InsightDetailResponse insightDetailResponse = insightApplicationService.detail(insightDetailQuery);
        log.info("Returning detail with insightId : {}", insightDetailResponse.getInsightId());
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
        return ResponseEntity.ok(insightDetailResponse);
    }
}
