package com.project.imdang.insight.domain.event;

import com.project.imdang.common.domain.event.DomainEventMessage;
import com.project.imdang.domain.event.DomainEvent;
import com.project.imdang.insight.domain.entity.Insight;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
public abstract class InsightEvent implements DomainEvent<Insight> {
    private final Insight insight;
    private final ZonedDateTime createdAt;
}
