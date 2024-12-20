package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightByAddressQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.SnapshotResponse;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RequestMapping("/my-insights")
@RequiredArgsConstructor
@RestController
public class MyInsightController {
// 보관함
    private final InsightApplicationService insightApplicationService;

    // 단지별 보기 : /my-insights/by-apartment-complex
    @GetMapping("/by-apartment-complex")
    public ResponseEntity<Page<SnapshotResponse>> listByApartmentComplex(@AuthenticationPrincipal UUID memberId, @ModelAttribute ListMyInsightByAddressQuery listMyInsightByAddressQuery) {
        // TODO - Spring Security, PagingQuery
        Map<ApartmentComplex, Page<SnapshotResponse>> apartmentComplexInsightResponsesMap = insightApplicationService.listMyInsightByAddress(listMyInsightByAddressQuery);
        return ResponseEntity.ok(apartmentComplexInsightResponsesMap);
    }

    // /my-visited-apartment-complexes
    @GetMapping("/by-address")
    public ResponseEntity<Map<ApartmentComplex, Page<SnapshotResponse>>> listByAddress(@ModelAttribute ListMyInsightByAddressQuery listMyInsightByAddressQuery) {
        // TODO - Spring Security, PagingQuery
        Map<ApartmentComplex, Page<SnapshotResponse>> apartmentComplexInsightResponsesMap = insightApplicationService.listMyInsightByAddress(listMyInsightByAddressQuery);
        return ResponseEntity.ok(apartmentComplexInsightResponsesMap);
    }

    // TODO - CHECK : 내가 작성한 인사이트는 불가!
    // /my-insights/exchanged
    @GetMapping("/by-address")
    public ResponseEntity<Map<ApartmentComplex, Page<SnapshotResponse>>> listByAddress(@ModelAttribute ListMyInsightByAddressQuery listMyInsightByAddressQuery) {
        // TODO - Spring Security, PagingQuery
        Map<ApartmentComplex, Page<SnapshotResponse>> apartmentComplexInsightResponsesMap = insightApplicationService.listMyInsightByAddress(listMyInsightByAddressQuery);
        return ResponseEntity.ok(apartmentComplexInsightResponsesMap);
    }

/*
    @GetMapping("/by-address")
    public ResponseEntity<Map<ApartmentComplex, Page<SnapshotResponse>>> listByAddress(@ModelAttribute ListMyInsightByAddressQuery listMyInsightByAddressQuery) {
        // TODO - Spring Security, PagingQuery
        Map<ApartmentComplex, Page<SnapshotResponse>> apartmentComplexInsightResponsesMap = insightApplicationService.listMyInsightByAddress(listMyInsightByAddressQuery);
        return ResponseEntity.ok(apartmentComplexInsightResponsesMap);
    }*/
}
