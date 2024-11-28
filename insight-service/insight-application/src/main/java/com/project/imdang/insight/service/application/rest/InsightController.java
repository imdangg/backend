package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByAddressResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByApartmentComplexResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.preview.PreviewInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.preview.PreviewInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequestMapping("/insights")
@RequiredArgsConstructor
@RestController
public class InsightController {

    private InsightApplicationService insightApplicationService;

    // 최신순, 인기순(추천수 순)
    // /insights?page=1&size=20&sort=createdAt,desc
    @GetMapping
    public ResponseEntity<ListInsightResponse> list() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/by-address")
    public ResponseEntity<ListInsightByAddressResponse> listByAddress() {
        return null;
    }

    @GetMapping("/by-my-visited-apartment-complex")
    public ResponseEntity<ListInsightByApartmentComplexResponse> listByMyVisitedApartmentComplex() {
        // "다녀온 단지가 없어요. 단지에 임장을 다녀오고 인사이트를 남겨보세요."
        return ResponseEntity.ok(null);
    }

    // TODO - CHECK : PathVariable vs RequestBody
    // TODO - detail/preview를 권한 체크해서 분리하는 것이 더 좋을까?
    // 상세, 프리뷰
    @GetMapping("/{insightId}/detail")
    public ResponseEntity<DetailInsightResponse> detail(@PathVariable("insightId") UUID insightId) {
        DetailInsightQuery detailInsightQuery = new DetailInsightQuery(insightId);
        DetailInsightResponse detailInsightResponse = insightApplicationService.detailInsight(detailInsightQuery);
        log.info("Returning detail of insight[id: {}].", detailInsightResponse.getInsightId());
        return ResponseEntity.ok(detailInsightResponse);
    }

    @GetMapping("/{insightId}/preview")
    public ResponseEntity<PreviewInsightResponse> preview(@PathVariable UUID insightId) {
        PreviewInsightQuery previewInsightQuery = new PreviewInsightQuery(insightId);
        PreviewInsightResponse previewInsightResponse = insightApplicationService.previewInsight(previewInsightQuery);
        log.info("Returning preview of insight[id: {}].", previewInsightResponse.getInsightId());
        return ResponseEntity.ok(previewInsightResponse);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateAndEvaluateInsightResponse> validateAndEvaluateInsight(@RequestBody ValidateAndEvaluateInsightCommand validateAndEvaluateInsightCommand) {
        ValidateAndEvaluateInsightResponse validateAndEvaluateInsightResponse = insightApplicationService.validateAndEvaluateInsight(validateAndEvaluateInsightCommand);
        log.info("Insight[id: {}] is validated and evaluated with score {}.", validateAndEvaluateInsightResponse.getInsightId(), validateAndEvaluateInsightResponse.getScore());
        return ResponseEntity.ok(validateAndEvaluateInsightResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateInsightResponse> createInsight(@RequestBody CreateInsightCommand createInsightCommand) {
        CreateInsightResponse createInsightResponse = insightApplicationService.createInsight(createInsightCommand);
        log.info("Insight[id: {}] is created.", createInsightResponse.getInsightId());
        return ResponseEntity.ok(createInsightResponse);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateInsightResponse> updateInsight(@RequestBody UpdateInsightCommand updateInsightCommand) {
        UpdateInsightResponse updateInsightResponse = insightApplicationService.updateInsight(updateInsightCommand);
        log.info("Insight[id: {}] is updated.", updateInsightResponse.getInsightId());
        return ResponseEntity.ok(updateInsightResponse);
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteInsightResponse> deleteInsight(@RequestBody DeleteInsightCommand deleteInsightCommand) {
        DeleteInsightResponse deleteInsightResponse = insightApplicationService.deleteInsight(deleteInsightCommand);
        log.info("Insight[id: {}] is deleted.", deleteInsightResponse.getInsightId());
        return ResponseEntity.ok(deleteInsightResponse);
    }

    @PostMapping("/recommend")
    public ResponseEntity<RecommendInsightResponse> recommendInsight(@RequestBody RecommendInsightCommand recommendInsightCommand) {
        RecommendInsightResponse recommendInsightResponse = insightApplicationService.recommendInsight(recommendInsightCommand);
        log.info("Insight[id: {}] is recommended.", recommendInsightResponse.getInsightId());
        return ResponseEntity.ok(recommendInsightResponse);
    }

    @PostMapping("/accuse")
    public ResponseEntity<AccuseInsightResponse> accuseInsight(@RequestBody AccuseInsightCommand accuseInsightCommand) {
        AccuseInsightResponse accuseInsightResponse = insightApplicationService.accuseInsight(accuseInsightCommand);
        log.info("Insight[id: {}] is accused.", accuseInsightResponse.getInsightId());
        return ResponseEntity.ok(accuseInsightResponse);
    }

    @PostMapping("/request")
    public ResponseEntity<RequestInsightResponse> requestInsight(@RequestBody RequestInsightCommand requestInsightCommand) {
        RequestInsightResponse requestInsightResponse = insightApplicationService.requestInsight(requestInsightCommand);
        log.info("Insight[id: {}] is requested.", requestInsightResponse.getInsightId());
        return ResponseEntity.ok(requestInsightResponse);
    }
}
