package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Request;
import com.project.imdang.insight.service.domain.event.InsightAccusedEvent;
import com.project.imdang.insight.service.domain.event.InsightDeletedEvent;
import com.project.imdang.insight.service.domain.event.InsightRequestedEvent;
import com.project.imdang.insight.service.domain.event.InsightUpdatedEvent;

public interface RequestDomainService {
    InsightRequestedEvent request(Request request);
}
