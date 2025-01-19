package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.insight.list.DistrictResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.MyInsightResponse;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import com.project.imdang.insight.service.domain.valueobject.District;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/my-insights")
@RequiredArgsConstructor
@RestController
public class MyInsightController {
// 보관함
    private final InsightApplicationService insightApplicationService;

    // 보관중인 인사이트 자치구 목록 API
    @GetMapping("/districts")
    public ResponseEntity<List<DistrictResponse>> listDistrict(@AuthenticationPrincipal UUID memberId) {
        List<DistrictResponse> districtResponses = insightApplicationService.listMyInsightDistrict(memberId);
        return ResponseEntity.ok(districtResponses);
    }

    @GetMapping("/by-district")
    public ResponseEntity<MyInsightResponse> countByDistrict(@AuthenticationPrincipal UUID memberId,
                                                             @ModelAttribute District district) {
        MyInsightResponse myInsightResponse = insightApplicationService.countMyInsightByDistrict(memberId, district);
        return ResponseEntity.ok(myInsightResponse);
    }

    // 전체 (내 인사이트 + 교환한 인사이트)
    // + 내 인사이트만 보기
    // + 단지별 보기
    @GetMapping
    public ResponseEntity<Page<InsightResponse>> list(@AuthenticationPrincipal UUID memberId,
                                                      @ModelAttribute District district,
                                                      @RequestParam(name = "apartmentComplexName", required = false) String apartmentComplexName,
                                                      @RequestParam(name = "onlyMine", defaultValue = "FALSE") Boolean onlyMine,
                                                      // TODO - PagingQuery
                                                      @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                      @RequestParam(name = "direction", defaultValue = "DESC") String direction,
                                                      @RequestParam(name = "properties", defaultValue = "created_at") String[] properties) {

        ListMyInsightQuery listMyInsightQuery = ListMyInsightQuery.builder()
                .memberId(memberId)
                .district(district)
                .apartmentComplexName(apartmentComplexName)
                .onlyMine(onlyMine)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .direction(direction)
                .properties(properties)
                .build();
        Page<InsightResponse> insights = insightApplicationService.listMyInsight(listMyInsightQuery);
        return ResponseEntity.ok(insights);
    }
}
