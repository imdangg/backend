package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import com.project.imdang.insight.service.domain.valueobject.District;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/districts")
@RequiredArgsConstructor
@RestController
public class DistrictController {
    // TODO - 캐싱
    public static final String DEFAULT_SI_DO = "서울특별시";
    private final InsightApplicationService insightApplicationService;

    @GetMapping("/si-gun-gu")
    public ResponseEntity<Page<District>> listSiGunGu(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<District> districts = insightApplicationService.listDistrict(DEFAULT_SI_DO, null, pageNumber, pageSize);
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/eup-myeon-dong")
    public ResponseEntity<Page<District>> listEupMyeonDong(
            @RequestParam(name = "siGunGu") String siGunGu,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<District> districts = insightApplicationService.listDistrict(DEFAULT_SI_DO, siGunGu, pageNumber, pageSize);
        return ResponseEntity.ok(districts);
    }
}
