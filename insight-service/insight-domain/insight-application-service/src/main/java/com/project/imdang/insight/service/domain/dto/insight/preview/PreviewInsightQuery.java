package com.project.imdang.insight.service.domain.dto.insight.preview;

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
public class PreviewInsightQuery {
    @NotNull
    private UUID insightId;
}
