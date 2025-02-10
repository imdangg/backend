package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.coupon.DetailMyCouponResponse;
import com.project.imdang.member.service.domain.ports.input.service.MemberCouponApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "CouponController", description = "쿠폰 API")
@RequestMapping("/my-coupons")
@Slf4j
public class MyCouponController {

    private final MemberCouponApplicationService memberCouponApplicationService;

    @Operation(description = "쿠폰 개수 조회 API")
    @ApiResponse(responseCode = "200", description = "쿠폰 개수 조회 성공",
            content = @Content(schema = @Schema(implementation = DetailMyCouponResponse.class)))
    @GetMapping("/detail")
    public ResponseEntity<DetailMyCouponResponse> detail(@AuthenticationPrincipal UUID memberId) {
        DetailMyCouponResponse detailMyCouponResponse = memberCouponApplicationService.detailMyCoupon(memberId);
        return ResponseEntity.ok(detailMyCouponResponse);
    }
}
