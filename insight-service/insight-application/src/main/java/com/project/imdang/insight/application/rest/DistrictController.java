package com.project.imdang.insight.application.rest;

import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.insight.domain.dto.insight.list.DistrictResult;
import com.project.imdang.insight.domain.ports.input.service.InsightApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.project.imdang.common.application.constant.Property.DEFAULT_SI_DO;
import static com.project.imdang.common.application.constant.RequestPath.LIST_EUP_MYEON_DONG;
import static com.project.imdang.common.application.constant.RequestPath.LIST_SI_GUN_GU;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DistrictController {
    // TODO - 캐싱
    private final InsightApplicationService insightApplicationService;

    @Operation(description = "시군구 목록 조회 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "시군구 목록 조회 성공")
    })
    @GetMapping(LIST_SI_GUN_GU)
    public ApiResponse<Page<DistrictResult>> listSiGunGu(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<DistrictResult> districts = insightApplicationService.listDistrict(DEFAULT_SI_DO, null, pageNumber, pageSize)
                .map(district -> DistrictResult.builder()
                        .siDo(district.getSiDo())
                        .siGunGu(district.getSiGunGu())
                        .eupMyeonDong(district.getEupMyeonDong())
                        .code(district.getCode())
                        .build());
        return ApiResponse.success(districts);
    }

    @Operation(description = "읍면동 목록 조회 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "읍면동 목록 조회 성공")
    })
    @GetMapping(LIST_EUP_MYEON_DONG)
    public ApiResponse<Page<DistrictResult>> listEupMyeonDong(
            @RequestParam(name = "siGunGu") String siGunGu,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<DistrictResult> districts = insightApplicationService.listDistrict(DEFAULT_SI_DO, siGunGu, pageNumber, pageSize)
                .map(district -> DistrictResult.builder()
                        .siDo(district.getSiDo())
                        .siGunGu(district.getSiGunGu())
                        .eupMyeonDong(district.getEupMyeonDong())
                        .code(district.getCode())
                        .build());
        return ApiResponse.success(districts);
    }
}
