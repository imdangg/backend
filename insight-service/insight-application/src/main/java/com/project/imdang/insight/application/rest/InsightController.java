package com.project.imdang.insight.application.rest;

import com.project.imdang.common.domain.valueobject.File;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.application.dto.insight.AccuseInsightRequest;
import com.project.imdang.insight.domain.dto.insight.list.ApartmentComplexResult;
import com.project.imdang.insight.application.dto.insight.CreateInsightRequest;
import com.project.imdang.insight.application.dto.insight.DeleteInsightRequest;
import com.project.imdang.insight.application.dto.insight.RecommendInsightRequest;
import com.project.imdang.insight.application.dto.insight.UpdateInsightRequest;
import com.project.imdang.insight.application.mapper.InsightRequestResolver;
import com.project.imdang.insight.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.domain.dto.insight.detail.InsightDetailResult;
import com.project.imdang.insight.domain.dto.insight.list.InsightResult;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightByApartmentComplexQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightByDateQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightByDistrictQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.domain.ports.input.service.InsightApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.project.imdang.common.application.constant.Property.DEFAULT_SI_DO;

@Slf4j
@RequestMapping("/insights")
@RequiredArgsConstructor
@RestController
@Tag(name = "InsightController", description = "인사이트 API")
public class InsightController {
    // TODO - 캐싱
    private final InsightApplicationService insightApplicationService;
    private final InsightRequestResolver resolver;

    // 내가 다녀온 apartmentComplexName 리스트 API
    @Operation(description = "내가 다녀온 아파트 단지 이름 목록 API")
    @ApiResponse(responseCode = "200", description = "내가 다녀온 아파트 단지 이름 목록 조회 성공")
    @GetMapping("/created-by-me/apartment-complexes")
    public ResponseEntity<List<ApartmentComplexResult>> listByMyVisitedApartmentComplex(@AuthenticationPrincipal UUID memberId) {
        List<ApartmentComplexResult> apartmentComplexes = insightApplicationService.listMyVisitedApartmentComplex(new MemberId(memberId)).stream()
                .map(apartmentComplex -> new ApartmentComplexResult(apartmentComplex.getName()))
                .toList();
        return ResponseEntity.ok(apartmentComplexes);
    }

    @Operation(description = "오늘 새롭게 올라온 인사이트 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "인사이트 목록이 조회되었습니다.")
    @GetMapping("/by-date")
    public ResponseEntity<Page<InsightResult>> listByDate(@RequestParam(name = "date", required = false) LocalDate date,
                                                          @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        ListInsightByDateQuery listInsightByDateQuery = ListInsightByDateQuery.builder()
                .date(date == null ? LocalDate.now() : date)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .direction("DESC")
                .properties(new String[]{"createdAt"})
                .build();
        Page<InsightResult> insightResults = insightApplicationService.listInsightByDate(listInsightByDateQuery);
        return ResponseEntity.ok(insightResults);
    }

    // 최신순, 인기순(추천수 순)
    // /insights?page=1&size=20&sort=createdAt,desc
    @Operation(description = "추천수 TOP 10 인사이트 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "인사이트 목록이 조회되었습니다.")
    @GetMapping
    public ResponseEntity<Page<InsightResult>> list(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        ListInsightQuery listInsightQuery = ListInsightQuery.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .direction("DESC")
                .properties(new String[]{"recommendedCount"})
                .build();
        Page<InsightResult> insightResults = insightApplicationService.listInsight(listInsightQuery);
        return ResponseEntity.ok(insightResults);
    }

    @GetMapping("/by-district")
    public ResponseEntity<Page<InsightResult>> listByDistrict(@RequestParam(name = "siGunGu") String siGunGu,
                                                              @RequestParam(name = "eupMyeonDong") String eupMyeonDong,
                                                              @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        ListInsightByDistrictQuery listInsightByDistrictQuery = ListInsightByDistrictQuery.builder()
                .siDo(DEFAULT_SI_DO)
                .siGunGu(siGunGu)
                .eupMyeonDong(eupMyeonDong)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .direction("DESC")
                .properties(new String[]{"createdAt"})
                .build();
        Page<InsightResult> insightResults = insightApplicationService.listInsightByDistrict(listInsightByDistrictQuery);
        return ResponseEntity.ok(insightResults);
    }

    @Operation(description = "아파트 단지별 인사이트 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "아파트 단지별 인사이트 목록이 조회되었습니다.")
    @GetMapping("/by-apartment-complex")
    public ResponseEntity<Page<InsightResult>> listByApartmentComplex(@RequestParam(name = "apartmentComplexName") String apartmentComplexName,
                                                                      @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery = ListInsightByApartmentComplexQuery.builder()
                .apartmentComplexName(apartmentComplexName)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .direction("DESC")
                .properties(new String[]{"createdAt"})
                .build();
        Page<InsightResult> insightResults = insightApplicationService.listInsightByApartmentComplex(listInsightByApartmentComplexQuery);
        return ResponseEntity.ok(insightResults);
    }

    // 상세, 프리뷰
    @Operation(description = "인사이트 상세 조회 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 조회 되었습니다.")
    @GetMapping("/detail")
    public ResponseEntity<InsightDetailResult> detail(@AuthenticationPrincipal UUID memberId,
                                                      @RequestParam(name = "insightId") UUID insightId) {
        DetailInsightQuery detailInsightQuery = new DetailInsightQuery(new InsightId(insightId), new MemberId(memberId));
        InsightDetailResult insightDetailResult = insightApplicationService.detailInsight(detailInsightQuery);
        log.info("Returning detail of insight[id: {}].", insightDetailResult.getInsightId());
        return ResponseEntity.ok(insightDetailResult);
    }

    @Operation(description = "인사이트 작성 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 작성 완료")
    @PostMapping("/create")
    public ResponseEntity<Void> createInsight(@AuthenticationPrincipal UUID memberId,
                                              @RequestPart("createInsightCommand") @Valid CreateInsightRequest createInsightRequest,
                                              @RequestPart("mainImage") MultipartFile mainImage) {
        File file = validateFile(mainImage);
        CreateInsightCommand createInsightCommand = resolver.toCreateInsightCommand(memberId, file, createInsightRequest);
        InsightId insightId = insightApplicationService.createInsight(createInsightCommand);
        log.info("Insight[id: {}] is created.", insightId);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "인사이트 수정 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 수정되었습니다.")
    @PostMapping("/update")
    public ResponseEntity<Void> updateInsight(@AuthenticationPrincipal UUID memberId,
                                               // TODO - CHANGE
                                               @RequestPart("updateInsightCommand") @Valid UpdateInsightRequest updateInsightRequest,
                                               @RequestPart(value = "mainImage", required = false) MultipartFile mainImage) {
        File file = validateFile(mainImage);
        UpdateInsightCommand updateInsightCommand = resolver.toUpdateInsightCommand(memberId, file, updateInsightRequest);
        InsightId insightId = insightApplicationService.updateInsight(updateInsightCommand);
        log.info("Insight[id: {}] is updated.", insightId);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "인사이트 삭제 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 삭제되었습니다.")
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteInsight(@AuthenticationPrincipal UUID memberId,
                                              @RequestBody @Valid DeleteInsightRequest deleteInsightRequest) {
        DeleteInsightCommand deleteInsightCommand = DeleteInsightCommand.builder()
                .memberId(new MemberId(memberId))
                .insightId(new InsightId(deleteInsightRequest.getInsightId()))
                .build();
        InsightId insightId = insightApplicationService.deleteInsight(deleteInsightCommand);
        log.info("Insight[id: {}] is deleted.", insightId);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "인사이트 추천 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 추천되었습니다.")
    @PostMapping("/recommend")
    public ResponseEntity<Void> recommendInsight(@AuthenticationPrincipal UUID memberId,
                                                 @RequestBody @Valid RecommendInsightRequest recommendInsightRequest) {

        RecommendInsightCommand recommendInsightCommand = RecommendInsightCommand.builder()
                .recommendMemberId(new MemberId(memberId))
                .insightId(new InsightId(recommendInsightRequest.getInsightId()))
                .build();
        InsightId insightId = insightApplicationService.recommendInsight(recommendInsightCommand);
        log.info("Insight[id: {}] is recommended.", insightId);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "인사이트 신고 API")
    @ApiResponse(responseCode = "200", description = "인사이트가 신고되었습니다.")
    @PostMapping("/accuse")
    public ResponseEntity<Void> accuseInsight(@AuthenticationPrincipal UUID memberId,
                                              @RequestBody @Valid AccuseInsightRequest accuseInsightRequest) {
        AccuseInsightCommand accuseInsightCommand = AccuseInsightCommand.builder()
                .accuseMemberId(new MemberId(memberId))
                .insightId(new InsightId(accuseInsightRequest.getInsightId()))
                .build();
        InsightId insightId = insightApplicationService.accuseInsight(accuseInsightCommand);
        log.info("Insight[id: {}] is accused.", insightId);
        return ResponseEntity.ok().build();
    }

    private File validateFile(MultipartFile file) {

        if (file.isEmpty()) {
            // TODO - 예외 처리
            throw new RuntimeException("No file detected.");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            // TODO - 예외 처리
            throw new RuntimeException("No filename.");
        }
        validateFileExtension(originalFilename);
        try {
            return File.builder()
                    .originalFilename(originalFilename)
                    .size(file.getSize())
                    .contentType(file.getContentType())
                    .inputStream(file.getInputStream())
                    .build();
        } catch (IOException e) {
            // TODO - 예외 처리
            throw new RuntimeException(e);
        }
    }

    private void validateFileExtension(String originalFilename) {
        int lastIndex = originalFilename.lastIndexOf(".");
        if (lastIndex == -1) {
            // TODO - 예외 처리
            throw new RuntimeException("The file has no extension.");
        }

        String extension = originalFilename.substring(lastIndex + 1).toLowerCase();
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");

        if (!allowedExtensions.contains(extension)) {
            // TODO - 예외 처리
            throw new RuntimeException("Invalid file type.");
        }
    }
}
