package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.application.client.ApartmentComplexApiResponse;
import com.project.imdang.insight.service.application.client.ApartmentComplexApiRestClient;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/apartment-complexes")
@RequiredArgsConstructor
@RestController
public class ApartmentComplexController {

    private final String API_KEY = "api-key";
    private final ApartmentComplexApiRestClient apartmentComplexApiRestClient;
    private final InsightApplicationService insightApplicationService;

    // 주소에 해당하는 apartmentComplexName 리스트 API
    @GetMapping
    public ResponseEntity<ApartmentComplexApiResponse> listByAddress(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "address") String address) {
        ApartmentComplexApiResponse response = apartmentComplexApiRestClient.getApartmentInfoList(pageNumber, pageSize, API_KEY, address);
        return ResponseEntity.ok(response);
    }

    // 내가 다녀온 apartmentComplexName 리스트 API
    @GetMapping("/my-visited")
    public ResponseEntity<List<ApartmentComplex>> listByMyVisited(@AuthenticationPrincipal UUID memberId
//            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
//            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        List<ApartmentComplex> apartmentComplexes = insightApplicationService.listMyVisitedApartmentComplex(memberId);
        return ResponseEntity.ok(apartmentComplexes);
    }
}
