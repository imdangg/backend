package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.Request;
import com.project.imdang.insight.service.domain.event.RequestCreatedEvent;

public interface RequestDomainService {
    RequestCreatedEvent request(Request request);
}
