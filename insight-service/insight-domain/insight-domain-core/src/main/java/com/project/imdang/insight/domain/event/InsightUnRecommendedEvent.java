package com.project.imdang.insight.domain.event;

import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.Recommend;
import lombok.Getter;

import java.time.ZonedDateTime;

public class InsightUnRecommendedEvent extends InsightEvent {
    @Getter
    private final Recommend recommend;

    public InsightUnRecommendedEvent(Insight insight, Recommend recommend, ZonedDateTime createdAt) {
        super(insight, createdAt);
        this.recommend = recommend;
    }
}
