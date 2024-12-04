package com.project.imdang.insight.service.domain.dto.insight.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DetailInsightQuery {
    private UUID insightId;
}
