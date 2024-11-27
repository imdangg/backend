package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.Request;
import com.project.imdang.insight.service.domain.event.InsightRequestedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestDomainServiceImpl implements RequestDomainService {

    @Override
    public InsightRequestedEvent request(Request request) {
        return null;
    }
}
