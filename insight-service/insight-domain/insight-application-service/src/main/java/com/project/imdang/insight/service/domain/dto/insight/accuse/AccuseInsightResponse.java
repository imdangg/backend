package com.project.imdang.insight.service.domain.dto.insight.accuse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AccuseInsightResponse {
    private UUID insightId;
}