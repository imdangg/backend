package com.project.imdang.insight.service.domain.dto.insight.detail;

import lombok.Getter;

import java.util.UUID;

@Getter
public class InsightDetailQuery {
    private final UUID insightId;

    public InsightDetailQuery(UUID insightId) {
        this.insightId = insightId;
    }
}
