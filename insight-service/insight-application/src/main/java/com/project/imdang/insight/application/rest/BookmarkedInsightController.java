package com.project.imdang.insight.application.rest;

import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.application.dto.insight.DistrictDTO;
import com.project.imdang.insight.domain.dto.insight.list.InsightResult;
import com.project.imdang.insight.domain.dto.insight.list.ListBookmarkedInsightQuery;
import com.project.imdang.insight.domain.dto.insight.list.ApartmentComplexOfBookmarkedInsightResult;
import com.project.imdang.insight.domain.dto.insight.list.DistrictOfBookmarkedInsightResult;
import com.project.imdang.insight.domain.ports.input.service.InsightApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.project.imdang.common.application.constant.RequestPath.LIST_APARTMENT_COMPLEX_OF_BOOKMARKED_INSIGHT;
import static com.project.imdang.common.application.constant.RequestPath.LIST_BOOKMARKED_INSIGHT;
import static com.project.imdang.common.application.constant.RequestPath.LIST_DISTRICT_OF_BOOKMARKED_INSIGHT;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookmarkedInsightController {
    // 보관함
    private final InsightApplicationService insightApplicationService;

    @Operation(description = "보관중인 인사이트의 자치구 목록 조회 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "보관중인 인사이트의 자치구 목록 조회 성공")
    })
    @GetMapping(LIST_DISTRICT_OF_BOOKMARKED_INSIGHT)
    public ApiResponse<List<DistrictOfBookmarkedInsightResult>> listDistrict(@AuthenticationPrincipal UUID memberId) {
        List<DistrictOfBookmarkedInsightResult> districtOfBookmarkedInsightResults
                = insightApplicationService.listBookmarkedInsightDistrict(new MemberId(memberId));
        return ApiResponse.success(districtOfBookmarkedInsightResults);
    }

    @Operation(description = "'단지별 보기' 클릭 시, 지역별 단지-인사이트 개수 목록 조회 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "'단지별 보기' 클릭 시, 지역별 단지-인사이트 개수 목록 조회 성공")
    })
    @GetMapping(LIST_APARTMENT_COMPLEX_OF_BOOKMARKED_INSIGHT)
    public ApiResponse<List<ApartmentComplexOfBookmarkedInsightResult>> listApartmentComplexByDistrict(@AuthenticationPrincipal UUID memberId,
                                                                                                       @ModelAttribute DistrictDTO district) {
        List<ApartmentComplexOfBookmarkedInsightResult> apartmentComplexOfBookmarkedInsightResults
                = insightApplicationService.listBookmarkedInsightApartmentComplexByDistrict(new MemberId(memberId), district.siDo(), district.siGunGu(), district.eupMyeonDong());
        return ApiResponse.success(apartmentComplexOfBookmarkedInsightResults);
    }

    @Operation(description = "보관한 인사이트 목록 조회 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "보관한 인사이트 목록 조회 성공")
    })
    @GetMapping(LIST_BOOKMARKED_INSIGHT)
    public ApiResponse<Page<InsightResult>> list(@AuthenticationPrincipal UUID memberId,
                                                 @ModelAttribute DistrictDTO district,
                                                 @RequestParam(name = "apartmentComplexName", required = false) String apartmentComplexName,
                                                 @RequestParam(name = "onlyMine", defaultValue = "FALSE") Boolean onlyMine,
                                                 // TODO - PagingQuery
                                                 @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                 @RequestParam(name = "direction", defaultValue = "DESC") String direction,
                                                 @RequestParam(name = "properties", defaultValue = "created_at") String[] properties) {

        ListBookmarkedInsightQuery listBookmarkedInsightQuery = ListBookmarkedInsightQuery.builder()
                .memberId(new MemberId(memberId))
                .siDo(district.siDo())
                .siGunGu(district.siGunGu())
                .eupMyeonDong(district.eupMyeonDong())
                .apartmentComplexName(apartmentComplexName)
                .onlyMine(onlyMine)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .direction(direction)
                .properties(properties)
                .build();
        Page<InsightResult> insights = insightApplicationService.listBookmarkedInsight(listBookmarkedInsightQuery);
        return ApiResponse.success(insights);
    }
}
