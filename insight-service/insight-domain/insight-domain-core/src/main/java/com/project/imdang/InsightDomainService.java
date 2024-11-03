package com.project.imdang;

import com.project.imdang.entity.Insight;
import com.project.imdang.event.InsightAccusedEvent;
import com.project.imdang.event.InsightDeletedEvent;
import com.project.imdang.valueobject.InsightId;

public interface InsightDomainService {
    InsightDeletedEvent delete(InsightId insightId);
    InsightAccusedEvent accuse(Insight insight);
}
