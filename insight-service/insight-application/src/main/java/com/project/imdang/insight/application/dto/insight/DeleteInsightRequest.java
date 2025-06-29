package com.project.imdang.insight.application.dto.insight;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteInsightRequest {
        @NotNull
        @Schema(description = "인사이트 ID")
        private UUID insightId;

        @Schema(description = "사용자 ID")
        private UUID memberId;
}
