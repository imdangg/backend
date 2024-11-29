package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByAddressResponse;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/my-insights")
@RequiredArgsConstructor
@RestController
public class MyInsightController {

    private InsightApplicationService insightApplicationService;

    @GetMapping("/by-address")
    public ResponseEntity<ListInsightByAddressResponse> listByAddress() {
        return null;
    }
}
