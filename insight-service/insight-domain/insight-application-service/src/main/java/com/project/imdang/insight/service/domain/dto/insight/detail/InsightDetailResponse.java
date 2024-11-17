package com.project.imdang.insight.service.domain.dto.insight.detail;

import lombok.Getter;

import java.util.UUID;

@Getter
public class InsightDetailResponse {
    private final UUID insightId;

    public InsightDetailResponse(UUID insightId) {
        this.insightId = insightId;
    }
}
