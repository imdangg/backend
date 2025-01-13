package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByApartmentComplexQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @ApiResponse(responseCode = "200", description = "인사이트 목록이 조회되었습니다.")
    @GetMapping
    public ResponseEntity<Page<InsightResponse>> list(@ModelAttribute ListInsightQuery listInsightQuery) {
        Page<InsightResponse> insightResponses = insightApplicationService.listInsight(listInsightQuery);
        return ResponseEntity.ok(insightResponses);
    }

    @Operation(description = "아파트 단지별 인사이트 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "아파트 단지별 인사이트 목록이 조회되었습니다.")
    @GetMapping("/by-apartment-complex")
    public ResponseEntity<Page<InsightResponse>> listByApartmentComplex(@ModelAttribute ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {
        Page<InsightResponse> insightResponses = insightApplicationService.listInsightByApartmentComplex(listInsightByApartmentComplexQuery);
        return ResponseEntity.ok(insightResponses);
    }

    // 상세, 프리뷰
    @Operation(description = "인사이트 상세 조회 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 조회 되었습니다.",
            content = @Content(schema = @Schema(implementation = DeleteInsightResponse.class)))
    @GetMapping("/detail")
    public ResponseEntity<DetailInsightResponse> detail(@AuthenticationPrincipal UUID memberId, @RequestParam(name = "insightId") UUID insightId) {
        DetailInsightQuery detailInsightQuery = new DetailInsightQuery(insightId, memberId);
        DetailInsightResponse detailInsightResponse = insightApplicationService.detailInsight(detailInsightQuery);
        log.info("Returning detail of insight[id: {}].", detailInsightResponse.getInsightId());
        return ResponseEntity.ok(detailInsightResponse);
    }

    // 프론트에서 작업
/*
    @Operation(description = "인사이트 평가 API")
    @ApiResponse(responseCode = "200", description = "인사이트 평가가 완료되었습니다.",
            content = @Content(schema = @Schema(implementation = ValidateAndEvaluateInsightResponse.class)))
    @PostMapping("/validate")
    public ResponseEntity<ValidateAndEvaluateInsightResponse> validateAndEvaluateInsight(@RequestBody ValidateAndEvaluateInsightCommand validateAndEvaluateInsightCommand) {
        ValidateAndEvaluateInsightResponse validateAndEvaluateInsightResponse = insightApplicationService.validateAndEvaluateInsight(validateAndEvaluateInsightCommand);
        log.info("Insight[id: {}] is validated and evaluated with score {}.", validateAndEvaluateInsightResponse.getInsightId(), validateAndEvaluateInsightResponse.getScore());
        return ResponseEntity.ok(validateAndEvaluateInsightResponse);
    }*/

    @Operation(description = "인사이트 작성 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 작성 완료",
            content = @Content(schema = @Schema(implementation = CreateInsightResponse.class)))
    @PostMapping("/create")
    public ResponseEntity<CreateInsightResponse> createInsight(@AuthenticationPrincipal UUID memberId,
                                                               @RequestPart("createInsightCommand") CreateInsightCommand createInsightCommand,
                                                               @RequestPart("mainImage") MultipartFile mainImage) {
        createInsightCommand.setMemberId(memberId);
        createInsightCommand.setMainImage(mainImage);
        CreateInsightResponse createInsightResponse = insightApplicationService.createInsight(createInsightCommand);
        log.info("Insight[id: {}] is created.", createInsightResponse.getInsightId());
        return ResponseEntity.ok(createInsightResponse);
    }

    @Operation(description = "인사이트 수정 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 수정되었습니다.",
            content = @Content(schema = @Schema(implementation = UpdateInsightResponse.class)))
    @PostMapping("/update")
    public ResponseEntity<UpdateInsightResponse> updateInsight(@AuthenticationPrincipal UUID memberId,
                                                               @RequestPart("updateInsightCommand") UpdateInsightCommand updateInsightCommand,
                                                               @RequestPart("mainImage") MultipartFile mainImage) {
        updateInsightCommand.setMemberId(memberId);
        updateInsightCommand.setMainImage(mainImage);
        UpdateInsightResponse updateInsightResponse = insightApplicationService.updateInsight(updateInsightCommand);
        log.info("Insight[id: {}] is updated.", updateInsightResponse.getInsightId());
        return ResponseEntity.ok(updateInsightResponse);
    }

    @Operation(description = "인사이트 삭제 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 삭제되었습니다.",
            content = @Content(schema = @Schema(implementation = DeleteInsightResponse.class)))
    @PostMapping("/delete")
    public ResponseEntity<DeleteInsightResponse> deleteInsight(@AuthenticationPrincipal UUID memberId, @RequestBody DeleteInsightCommand deleteInsightCommand) {
        deleteInsightCommand.setMemberId(memberId);
        DeleteInsightResponse deleteInsightResponse = insightApplicationService.deleteInsight(deleteInsightCommand);
        log.info("Insight[id: {}] is deleted.", deleteInsightResponse.getInsightId());
        return ResponseEntity.ok(deleteInsightResponse);
    }

    @Operation(description = "인사이트 추천 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 추천되었습니다.",
            content = @Content(schema = @Schema(implementation = RecommendInsightResponse.class)))
    @PostMapping("/recommend")
    public ResponseEntity<RecommendInsightResponse> recommendInsight(@AuthenticationPrincipal UUID memberId, @RequestBody RecommendInsightCommand recommendInsightCommand) {
        recommendInsightCommand.setMemberId(memberId);
        RecommendInsightResponse recommendInsightResponse = insightApplicationService.recommendInsight(recommendInsightCommand);
        log.info("Insight[id: {}] is recommended.", recommendInsightResponse.getInsightId());
        return ResponseEntity.ok(recommendInsightResponse);
    }

    @Operation(description = "인사이트 신고 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 신고되었습니다.",
            content = @Content(schema = @Schema(implementation = AccuseInsightResponse.class)))
    @PostMapping("/accuse")
    public ResponseEntity<AccuseInsightResponse> accuseInsight(@AuthenticationPrincipal UUID memberId, @RequestBody AccuseInsightCommand accuseInsightCommand) {
        accuseInsightCommand.setAccuseMemberId(memberId);
        AccuseInsightResponse accuseInsightResponse = insightApplicationService.accuseInsight(accuseInsightCommand);
        log.info("Insight[id: {}] is accused.", accuseInsightResponse.getInsightId());
        return ResponseEntity.ok(accuseInsightResponse);
    }
}
