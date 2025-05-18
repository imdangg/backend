package com.project.imdang.insight.domain.event;

import com.project.imdang.insight.domain.entity.Accuse;
import com.project.imdang.insight.domain.entity.Insight;
import lombok.Getter;

import java.time.ZonedDateTime;

public class InsightAccusedEvent extends InsightEvent {
    @Getter
    private final Accuse accuse;

    public InsightAccusedEvent(Insight insight, Accuse accuse, ZonedDateTime createdAt) {
        super(insight, createdAt);
        this.accuse = accuse;
    }
}
