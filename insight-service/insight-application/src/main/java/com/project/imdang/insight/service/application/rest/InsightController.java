package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.InsightDetailRequest;
import com.project.imdang.insight.service.domain.dto.InsightDetailResponse;
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

    // /insights - 최신순, 인기순
    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(null);
    }

    // 상세
    @GetMapping("/{insightId}")
    public ResponseEntity<InsightDetailResponse> detail(@PathVariable UUID insightId) {
        InsightDetailRequest insightDetailRequest = new InsightDetailRequest(insightId);
        InsightDetailResponse insightDetailResponse = insightApplicationService.detail(insightDetailRequest);
        log.info("Returning detail with insightId : {}", insightDetailResponse.getInsightId());
        return ResponseEntity.ok(insightDetailResponse);
    }
}
