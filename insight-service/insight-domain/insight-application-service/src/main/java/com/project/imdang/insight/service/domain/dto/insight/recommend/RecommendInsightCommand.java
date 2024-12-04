package com.project.imdang.insight.service.domain.dto.insight.recommend;

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
public class RecommendInsightCommand {
    @NotNull
    private UUID insightId;
}
