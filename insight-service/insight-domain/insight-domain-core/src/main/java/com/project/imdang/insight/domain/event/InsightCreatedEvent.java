package com.project.imdang.insight.domain.event;

import com.project.imdang.insight.domain.entity.Insight;

import java.time.ZonedDateTime;

public class InsightCreatedEvent extends InsightEvent {
    public InsightCreatedEvent(Insight insight, ZonedDateTime createdAt) {
        super(insight, createdAt);
    }
}
