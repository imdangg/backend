package com.project.imdang.insight.domain.event;

import com.project.imdang.insight.domain.entity.Insight;

import java.time.ZonedDateTime;

public class InsightDeletedEvent extends InsightEvent {
    public InsightDeletedEvent(Insight insight, ZonedDateTime createdAt) {
        super(insight, createdAt);
    }
}
