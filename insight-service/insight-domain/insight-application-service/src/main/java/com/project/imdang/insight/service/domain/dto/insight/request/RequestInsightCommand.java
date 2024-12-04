package com.project.imdang.insight.service.domain.dto.insight.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RequestInsightCommand {
    @NotNull
    private UUID insightId;
    @NotNull
    private Long memberCouponId;
    // requestedBy
    @NotNull
    private UUID requestMemberId;
}
