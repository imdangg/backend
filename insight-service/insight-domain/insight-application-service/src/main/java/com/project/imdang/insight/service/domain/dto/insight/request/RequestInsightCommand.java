package com.project.imdang.insight.service.domain.dto.insight.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestInsightCommand {
    @NotNull
    private UUID requestedInsightId;
    @NotNull
    private Long memberCouponId;
    // requestedBy
    @NotNull
    private UUID requestMemberId;
}
