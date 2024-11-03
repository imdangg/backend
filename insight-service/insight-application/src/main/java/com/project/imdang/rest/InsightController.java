package com.project.imdang.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/insights")
@RequiredArgsConstructor
@RestController
public class InsightController {

//    private final InsightCommandApplicationService insightCommandApplicationService;
//    private final InsightQueryApplicationService insightQueryApplicationService;

    // /insights - 최신순, 인기순

    // 상세
    @GetMapping("/{insightId}")
    public ResponseEntity<?> detail() {
        return new ResponseEntity<>(null);
    }
}
