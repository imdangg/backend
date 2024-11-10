package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.event.InsightAccusedEvent;
import com.project.imdang.insight.service.domain.event.InsightDeletedEvent;
import com.project.imdang.domain.valueobject.InsightId;

public interface InsightDomainService {
    InsightDeletedEvent delete(InsightId insightId);
    InsightAccusedEvent accuse(Insight insight);
}
