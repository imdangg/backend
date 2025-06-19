package com.project.imdang.insight.application.dto.insight;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccuseInsightRequest {
        // insightId - accuseMemberId UNIQUE
        @NotNull
        @Schema(description = "인사이트 ID")
        private UUID insightId;

        @Schema(description = "신고한 사용자 ID")
        private UUID accuseMemberId;    // accusedBy : 신고한 memberId
}
