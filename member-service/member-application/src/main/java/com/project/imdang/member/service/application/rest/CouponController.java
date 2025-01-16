package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.coupon.*;
import com.project.imdang.member.service.domain.ports.input.service.MemberCouponApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "CouponController", description = "쿠폰 API")
@RequestMapping("/coupons")
@Slf4j
public class CouponController {

    private final MemberCouponApplicationService memberCouponApplicationService;

    @Operation(description = "쿠폰 개수(목록) 조회 API")
    @ApiResponse(responseCode = "200", description = "쿠폰 개수 조회 성공",
            content = @Content(schema = @Schema(implementation = ListMemberCouponResponse.class)))
    @GetMapping()
    public ResponseEntity<ListMemberCouponResponse> list(@AuthenticationPrincipal UUID memberId) {
        ListMemberCouponResponse listMemberCouponResponse = memberCouponApplicationService.listMemberCoupon(memberId);
        return ResponseEntity.ok(listMemberCouponResponse);
    }

    @Operation(description = "쿠폰 발행 API")
    @ApiResponse(responseCode = "200", description = "쿠폰 발행 성공")
    @PostMapping("/issue")
    public ResponseEntity<Void> issueCoupon(@AuthenticationPrincipal UUID memberId, @RequestBody @Valid IssueMemberCouponCommand issueMemberCouponCommand) {
        issueMemberCouponCommand.setMemberId(memberId);
        memberCouponApplicationService.issueMemberCoupon(issueMemberCouponCommand);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "쿠폰 사용 API")
    @ApiResponse(responseCode = "200", description = "쿠폰 사용 성공",
            content = @Content(schema = @Schema(implementation = UseMemberCouponCommandResponse.class))
    )
    @PostMapping("/use")
    public ResponseEntity<UseMemberCouponCommandResponse> useCoupon(@AuthenticationPrincipal UUID memberId) {
        UseMemberCouponCommandResponse useMemberCouponCommandResponse = memberCouponApplicationService.useMemberCoupon(new UseMemberCouponCommand(memberId));
        return ResponseEntity.ok(useMemberCouponCommandResponse);
    }

    @Operation(description = "쿠폰 사용 취소 API")
    @ApiResponse(responseCode = "200", description = "쿠폰 사용 취소 성공")
    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelCoupon(@RequestBody CancelMemberCouponCommand cancelMemberCouponCommand) {
        memberCouponApplicationService.cancleMemberCoupon(cancelMemberCouponCommand);
        return ResponseEntity.ok().build();
    }
}
