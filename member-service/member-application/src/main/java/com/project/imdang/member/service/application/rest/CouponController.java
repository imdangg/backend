package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.coupon.IssueMemberCouponCommand;
import com.project.imdang.member.service.domain.ports.input.service.MemberCouponApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "CouponController", description = "쿠폰 API")
@RequestMapping("/coupons")
@Slf4j
public class CouponController {

    private final MemberCouponApplicationService memberCouponApplicationService;

    @Operation(description = "쿠폰 발행 API")
    @ApiResponse(responseCode = "200", description = "쿠폰 발행 성공")
    @PostMapping("/issue")
    public ResponseEntity<Void> issue(@AuthenticationPrincipal UUID memberId) {
        memberCouponApplicationService.issueMemberCoupon(new IssueMemberCouponCommand(memberId, "Welcome"));
        return ResponseEntity.ok().build();
    }
}
