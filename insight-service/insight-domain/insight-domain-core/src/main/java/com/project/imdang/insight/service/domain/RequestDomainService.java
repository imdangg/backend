package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.Request;
import com.project.imdang.insight.service.domain.event.InsightRequestedEvent;

public interface RequestDomainService {
    InsightRequestedEvent request(Request request);
}
