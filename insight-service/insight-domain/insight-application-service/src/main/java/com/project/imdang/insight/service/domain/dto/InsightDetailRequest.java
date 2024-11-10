package com.project.imdang.insight.service.domain.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class InsightDetailRequest {
    private final UUID insightId;

    public InsightDetailRequest(UUID insightId) {
        this.insightId = insightId;
    }
}
