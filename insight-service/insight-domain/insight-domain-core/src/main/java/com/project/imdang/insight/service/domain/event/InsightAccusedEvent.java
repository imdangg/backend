package com.project.imdang.insight.service.domain.event;

import com.project.imdang.insight.service.domain.entity.Insight;

import java.time.ZonedDateTime;

public class InsightAccusedEvent extends InsightEvent {
    public InsightAccusedEvent(Insight insight, ZonedDateTime createdAt) {
        super(insight, createdAt);
    }
}
