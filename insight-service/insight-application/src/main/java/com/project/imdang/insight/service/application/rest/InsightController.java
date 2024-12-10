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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RequestMapping("/insights")
@RequiredArgsConstructor
@RestController
@Tag(name = "InsightController", description = "인사이트 API")
public class InsightController {
// TODO - 캐싱
    private final InsightApplicationService insightApplicationService;

    // 오늘 새롭게 올라온 인사이트
    // 추천수가 가장 많은 인사이트 TOP 10
    // 최신순, 인기순(추천수 순)
    // /insights?page=1&size=20&sort=createdAt,desc
    @Operation(description = "오늘 새롭게 올라온 인사이트 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "인사이트 목록이 조회되었습니다.",
            content = @Content(schema = @Schema(implementation = InsightResponse.class)))
    @GetMapping
    public ResponseEntity<Page<InsightResponse>> list(@ModelAttribute ListInsightQuery listInsightQuery) {
        Page<InsightResponse> insightResponses = insightApplicationService.listInsight(listInsightQuery);
        return ResponseEntity.ok(insightResponses);
    }

    // 목록
    @Operation(description = "주소별 인사이트 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "주소별 인사이트 목록이 조회되었습니다.",
            content = @Content(schema = @Schema(implementation = InsightResponse.class)))
    @GetMapping("/by-address")
    public ResponseEntity<Map<ApartmentComplex, Page<InsightResponse>>> listByAddress(@ModelAttribute ListInsightByAddressQuery listInsightByAddressQuery) {
        Map<ApartmentComplex, Page<InsightResponse>> apartmentComplexInsightResponsesMap = insightApplicationService.listInsightByAddress(listInsightByAddressQuery);
        return ResponseEntity.ok(apartmentComplexInsightResponsesMap);
    }

    @Operation(description = "아파트 단지별 인사이트 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "아파트 단지별 인사이트 목록이 조회되었습니다.",
            content = @Content(schema = @Schema(implementation = InsightResponse.class)))
    @GetMapping("/by-apartment-complex")
    public ResponseEntity<Page<InsightResponse>> listByApartmentComplex(@ModelAttribute ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {
        Page<InsightResponse> insightResponses = insightApplicationService.listInsightByApartmentComplex(listInsightByApartmentComplexQuery);
        return ResponseEntity.ok(insightResponses);
    }

    // 내가 다녀온 단지
    @Operation(description = "내가 작성한 아파트 단지별 인사이트 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "내가 작성한 아파트 단지별 인사이트 목록이 조회되었습니다")
    @GetMapping("/by-my-visited-apartment-complex")
    public ResponseEntity<Map<ApartmentComplex, Page<InsightResponse>>> listByMyVisitedApartmentComplex(@ModelAttribute ListInsightQuery listInsightQuery) {
        // "다녀온 단지가 없어요. 단지에 임장을 다녀오고 인사이트를 남겨보세요."
        Map<ApartmentComplex, Page<InsightResponse>> apartmentComplexInsightResponsesMap = insightApplicationService.listInsightByMyVisitedApartmentComplex(listInsightQuery);
        return ResponseEntity.ok(apartmentComplexInsightResponsesMap);
    }

    // TODO - CHECK : PathVariable vs RequestBody
    // TODO - detail/preview를 권한 체크해서 분리하는 것이 더 좋을까?
    // 상세, 프리뷰
    @Operation(description = "인사이트 상세 조회 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 조회 되었습니다.",
            content = @Content(schema = @Schema(implementation = DeleteInsightResponse.class)))
    @GetMapping("/{insightId}/detail")
    public ResponseEntity<DetailInsightResponse> detail(@PathVariable("insightId") UUID insightId) {
        DetailInsightQuery detailInsightQuery = new DetailInsightQuery(insightId);
        DetailInsightResponse detailInsightResponse = insightApplicationService.detailInsight(detailInsightQuery);
        log.info("Returning detail of insight[id: {}].", detailInsightResponse.getInsightId());
        return ResponseEntity.ok(detailInsightResponse);
    }

    @Operation(description = "인사이트 미리보기 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 미리보기 되었습니다.",
            content = @Content(schema = @Schema(implementation = PreviewInsightResponse.class)))
    @GetMapping("/{insightId}/preview")
    public ResponseEntity<PreviewInsightResponse> preview(@PathVariable UUID insightId) {
        PreviewInsightQuery previewInsightQuery = new PreviewInsightQuery(insightId);
        PreviewInsightResponse previewInsightResponse = insightApplicationService.previewInsight(previewInsightQuery);
        log.info("Returning preview of insight[id: {}].", previewInsightResponse.getInsightId());
        return ResponseEntity.ok(previewInsightResponse);
    }

    @Operation(description = "인사이트 평가 API")
    @ApiResponse(responseCode = "200", description = "인사이트 평가가 완료되었습니다.",
            content = @Content(schema = @Schema(implementation = ValidateAndEvaluateInsightResponse.class)))
    @PostMapping("/validate")
    public ResponseEntity<ValidateAndEvaluateInsightResponse> validateAndEvaluateInsight(@RequestBody ValidateAndEvaluateInsightCommand validateAndEvaluateInsightCommand) {
        ValidateAndEvaluateInsightResponse validateAndEvaluateInsightResponse = insightApplicationService.validateAndEvaluateInsight(validateAndEvaluateInsightCommand);
        log.info("Insight[id: {}] is validated and evaluated with score {}.", validateAndEvaluateInsightResponse.getInsightId(), validateAndEvaluateInsightResponse.getScore());
        return ResponseEntity.ok(validateAndEvaluateInsightResponse);
    }

    @Operation(description = "인사이트 작성 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 작성되었습니다.",
            content = @Content(schema = @Schema(implementation = CreateInsightResponse.class)))
    @PostMapping("/create")
    public ResponseEntity<CreateInsightResponse> createInsight(@RequestBody CreateInsightCommand createInsightCommand) {
        CreateInsightResponse createInsightResponse = insightApplicationService.createInsight(createInsightCommand);
        log.info("Insight[id: {}] is created.", createInsightResponse.getInsightId());
        return ResponseEntity.ok(createInsightResponse);
    }

    @Operation(description = "인사이트 수정 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 수정되었습니다.",
            content = @Content(schema = @Schema(implementation = UpdateInsightResponse.class)))
    @PostMapping("/update")
    public ResponseEntity<UpdateInsightResponse> updateInsight(@RequestBody UpdateInsightCommand updateInsightCommand) {
        UpdateInsightResponse updateInsightResponse = insightApplicationService.updateInsight(updateInsightCommand);
        log.info("Insight[id: {}] is updated.", updateInsightResponse.getInsightId());
        return ResponseEntity.ok(updateInsightResponse);
    }

    @Operation(description = "인사이트 삭제 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 삭제되었습니다.",
            content = @Content(schema = @Schema(implementation = DeleteInsightResponse.class)))
    @PostMapping("/delete")
    public ResponseEntity<DeleteInsightResponse> deleteInsight(@RequestBody DeleteInsightCommand deleteInsightCommand) {
        DeleteInsightResponse deleteInsightResponse = insightApplicationService.deleteInsight(deleteInsightCommand);
        log.info("Insight[id: {}] is deleted.", deleteInsightResponse.getInsightId());
        return ResponseEntity.ok(deleteInsightResponse);
    }

    @Operation(description = "인사이트 추천 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 추천되었습니다.",
            content = @Content(schema = @Schema(implementation = RecommendInsightResponse.class)))
    @PostMapping("/recommend")
    public ResponseEntity<RecommendInsightResponse> recommendInsight(@RequestBody RecommendInsightCommand recommendInsightCommand) {
        RecommendInsightResponse recommendInsightResponse = insightApplicationService.recommendInsight(recommendInsightCommand);
        log.info("Insight[id: {}] is recommended.", recommendInsightResponse.getInsightId());
        return ResponseEntity.ok(recommendInsightResponse);
    }

    @Operation(description = "인사이트 신고 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 신고되었습니다.",
            content = @Content(schema = @Schema(implementation = AccuseInsightResponse.class)))
    @PostMapping("/accuse")
    public ResponseEntity<AccuseInsightResponse> accuseInsight(@RequestBody AccuseInsightCommand accuseInsightCommand) {
        AccuseInsightResponse accuseInsightResponse = insightApplicationService.accuseInsight(accuseInsightCommand);
        log.info("Insight[id: {}] is accused.", accuseInsightResponse.getInsightId());
        return ResponseEntity.ok(accuseInsightResponse);
    }

    @Operation(description = "쿠폰으로 인사이트 요청 API")
    @ApiResponse(responseCode = "200", description = "쿠폰으로 인사이트가 요청되었습니다.",
            content = @Content(schema = @Schema(implementation = RequestInsightResponse.class)))
    @PostMapping("/request")
    public ResponseEntity<RequestInsightResponse> requestInsight(@RequestBody RequestInsightCommand requestInsightCommand) {
        RequestInsightResponse requestInsightResponse = insightApplicationService.requestInsight(requestInsightCommand);
        log.info("Insight[id: {}] is requested.", requestInsightResponse.getInsightId());
        return ResponseEntity.ok(requestInsightResponse);
    }
}
