package com.project.imdang.insight.service.domain.dto.insight.detail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DetailInsightQuery {
    @NotNull
    private UUID insightId;
}
