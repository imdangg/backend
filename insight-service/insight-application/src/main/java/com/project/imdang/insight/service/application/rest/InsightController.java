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
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByAddressQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByApartmentComplexQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.preview.PreviewInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.preview.PreviewInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequestMapping("/insights")
@RequiredArgsConstructor
@RestController
public class InsightController {
// TODO - 캐싱
    private final InsightApplicationService insightApplicationService;

    // 오늘 새롭게 올라온 인사이트
    // 추천수가 가장 많은 인사이트 TOP 10
    // 최신순, 인기순(추천수 순)
    // /insights?page=1&size=20&sort=createdAt,desc
    @GetMapping
    public ResponseEntity<List<InsightResponse>> list(@ModelAttribute ListInsightQuery listInsightQuery) {
        List<InsightResponse> insightResponses = insightApplicationService.listInsight(listInsightQuery);
        return ResponseEntity.ok(insightResponses);
    }

    // 목록
    @GetMapping("/by-address")
    public ResponseEntity<Map<ApartmentComplex, List<InsightResponse>>> listByAddress(@ModelAttribute ListInsightByAddressQuery listInsightByAddressQuery) {
        Map<ApartmentComplex, List<InsightResponse>> apartmentComplexInsightResponsesMap = insightApplicationService.listInsightByAddress(listInsightByAddressQuery);
        return ResponseEntity.ok(apartmentComplexInsightResponsesMap);
    }

    @GetMapping("/by-apartment-complex")
    public ResponseEntity<List<InsightResponse>> listByApartmentComplex(@ModelAttribute ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {
        List<InsightResponse> insightResponses = insightApplicationService.listInsightByApartmentComplex(listInsightByApartmentComplexQuery);
        return ResponseEntity.ok(insightResponses);
    }

    // 내가 다녀온 단지
    @GetMapping("/by-my-visited-apartment-complex")
    public ResponseEntity<Map<ApartmentComplex, List<InsightResponse>>> listByMyVisitedApartmentComplex(@ModelAttribute ListInsightQuery listInsightQuery) {
        // "다녀온 단지가 없어요. 단지에 임장을 다녀오고 인사이트를 남겨보세요."
        Map<ApartmentComplex, List<InsightResponse>> apartmentComplexInsightResponsesMap = insightApplicationService.listInsightByMyVisitedApartmentComplex(listInsightQuery);
        return ResponseEntity.ok(apartmentComplexInsightResponsesMap);
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
