package com.project.imdang.insight.application.rest;

import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.insight.domain.dto.insight.list.DistrictResult;
import com.project.imdang.insight.domain.ports.input.service.InsightApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.project.imdang.common.application.Property.DEFAULT_SI_DO;

@Slf4j
@RequestMapping("/districts")
@RequiredArgsConstructor
@RestController
public class DistrictController {
    // TODO - 캐싱
    private final InsightApplicationService insightApplicationService;

    /**
     * 시군구 목록 조회 API
     */
    @GetMapping("/si-gun-gu")
    public ResponseEntity<ApiResponse<Page<DistrictResult>>> listSiGunGu(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<DistrictResult> districts = insightApplicationService.listDistrict(DEFAULT_SI_DO, null, pageNumber, pageSize)
                .map(district -> DistrictResult.builder()
                        .siDo(district.getSiDo())
                        .siGunGu(district.getSiGunGu())
                        .eupMyeonDong(district.getEupMyeonDong())
                        .code(district.getCode())
                        .build());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(districts));
    }

    /**
     * 읍면동 목록 조회 API
     */
    @GetMapping("/eup-myeon-dong")
    public ResponseEntity<ApiResponse<Page<DistrictResult>>> listEupMyeonDong(
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
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(districts));
    }
}
