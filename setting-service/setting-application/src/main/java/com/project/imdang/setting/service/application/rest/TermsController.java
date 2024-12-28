package com.project.imdang.setting.service.application.rest;

import com.project.imdang.setting.service.domain.dto.AgreeTermsCommand;
import com.project.imdang.setting.service.domain.dto.TermsResponse;
import com.project.imdang.setting.service.domain.ports.input.service.TermsApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/terms")
@RequiredArgsConstructor
@RestController
public class TermsController {

    private final TermsApplicationService termsApplicationService;

    @GetMapping
    public ResponseEntity<List<TermsResponse>> list() {
        List<TermsResponse> list = termsApplicationService.listTerms();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/agree")
    public ResponseEntity<Void> agree(@AuthenticationPrincipal UUID memberId, @RequestBody AgreeTermsCommand agreeTermsCommand) {
        agreeTermsCommand.setMemberId(memberId);
        termsApplicationService.agreeTerms(agreeTermsCommand);
        return ResponseEntity.ok().build();
    }
}
