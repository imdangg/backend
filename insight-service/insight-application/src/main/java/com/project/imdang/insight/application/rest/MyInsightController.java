package com.project.imdang.insight.application.rest;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.application.dto.insight.DistrictDTO;
import com.project.imdang.insight.domain.dto.insight.list.MyApartmentComplexResult;
import com.project.imdang.insight.domain.dto.insight.list.MyDistrictResult;
import com.project.imdang.insight.domain.dto.insight.list.InsightResult;
import com.project.imdang.insight.domain.dto.insight.list.InsightSimpleResult;
import com.project.imdang.insight.domain.dto.insight.list.ListMyInsightCreatedByMeQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListMyInsightQuery;
import com.project.imdang.insight.domain.ports.input.service.InsightApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/my-insights")
@RequiredArgsConstructor
@RestController
public class MyInsightController {
// 보관함
    private final InsightApplicationService insightApplicationService;

    // 보관중인 인사이트 자치구 목록 API
    @GetMapping("/districts")
    public ResponseEntity<List<MyDistrictResult>> listDistrict(@AuthenticationPrincipal UUID memberId) {
        List<MyDistrictResult> myDistrictResults = insightApplicationService.listMyInsightDistrict(new MemberId(memberId));
        return ResponseEntity.ok(myDistrictResults);
    }

    // '단지별 보기' 클릭 시, 자치구별 단지-인사이트 개수 목록 API
    /*
    {
        "apartmentComplexName": "신뇬현 더 센트럴 푸르지오",
        "insightCount": 12
    }
     */
    @GetMapping("/by-district/apartment-complexes")
    public ResponseEntity<List<MyApartmentComplexResult>> listApartmentComplexByDistrict(@AuthenticationPrincipal UUID memberId,
                                                                                         @ModelAttribute DistrictDTO district) {
        List<MyApartmentComplexResult> myApartmentComplexResults
                = insightApplicationService.listMyInsightApartmentComplexByDistrict(new MemberId(memberId), district.siDo(), district.siGunGu(), district.eupMyeonDong());
        return ResponseEntity.ok(myApartmentComplexResults);
    }

    // 전체 (내 인사이트 + 교환한 인사이트)
    // + 내 인사이트만 보기
    // + 단지별 보기
    @GetMapping
    public ResponseEntity<Page<InsightResult>> list(@AuthenticationPrincipal UUID memberId,
                                                    @ModelAttribute DistrictDTO district,
                                                    @RequestParam(name = "apartmentComplexName", required = false) String apartmentComplexName,
                                                    @RequestParam(name = "onlyMine", defaultValue = "FALSE") Boolean onlyMine,
                                                    // TODO - PagingQuery
                                                    @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    @RequestParam(name = "direction", defaultValue = "DESC") String direction,
                                                    @RequestParam(name = "properties", defaultValue = "created_at") String[] properties) {

        ListMyInsightQuery listMyInsightQuery = ListMyInsightQuery.builder()
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
        Page<InsightResult> insights = insightApplicationService.listMyInsight(listMyInsightQuery);
        return ResponseEntity.ok(insights);
    }

    @GetMapping("/created-by-me")
    public ResponseEntity<Page<InsightSimpleResult>> listCreatedByMe(@AuthenticationPrincipal UUID memberId,
                                                                     // TODO - PagingQuery
                                                                     @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                                     @RequestParam(name = "direction", defaultValue = "DESC") String direction,
                                                                     @RequestParam(name = "properties", defaultValue = "created_at") String[] properties) {

        ListMyInsightCreatedByMeQuery listMyInsightCreatedByMeQuery = ListMyInsightCreatedByMeQuery.builder()
                .memberId(new MemberId(memberId))
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .direction(direction)
                .properties(properties)
                .build();
        Page<InsightSimpleResult> insights = insightApplicationService.listMyInsightCreatedByMe(listMyInsightCreatedByMeQuery);
        return ResponseEntity.ok(insights);
    }
}
