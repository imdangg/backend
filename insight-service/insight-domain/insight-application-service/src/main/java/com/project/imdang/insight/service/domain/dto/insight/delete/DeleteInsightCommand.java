package com.project.imdang.insight.service.domain.dto.insight.delete;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteInsightCommand {
    @NotNull
    @Schema(description = "인사이트ID")
    private UUID insightId;
    @Setter
    @Schema(description = "사용자ID")
    private UUID memberId;
}
