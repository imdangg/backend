package com.project.imdang.insight.service.domain.event;

import com.project.imdang.insight.service.domain.entity.Insight;

import java.time.ZonedDateTime;

public class InsightUpdatedEvent extends InsightEvent {
    public InsightUpdatedEvent(Insight insight, ZonedDateTime createdAt) {
        super(insight, createdAt);
    }
}
