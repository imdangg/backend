package com.project.imdang.insight.service.domain.event;

import com.project.imdang.insight.service.domain.entity.Insight;

import java.time.ZonedDateTime;

public class InsightRequestedEvent extends InsightEvent {
    public InsightRequestedEvent(Insight insight, ZonedDateTime createdAt) {
        super(insight, createdAt);
    }
}
