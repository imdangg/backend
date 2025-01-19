package com.project.imdang.insight.service.domain.event;

import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Recommend;
import lombok.Getter;

import java.time.ZonedDateTime;

public class InsightRecommendedEvent extends InsightEvent {
    @Getter
    private final Recommend recommend;

    public InsightRecommendedEvent(Insight insight, Recommend recommend, ZonedDateTime createdAt) {
        super(insight, createdAt);
        this.recommend = recommend;
    }
}
