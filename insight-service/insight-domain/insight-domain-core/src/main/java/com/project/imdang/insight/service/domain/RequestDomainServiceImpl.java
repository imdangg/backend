package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.Request;
import com.project.imdang.insight.service.domain.event.RequestCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

@Slf4j
public class RequestDomainServiceImpl implements RequestDomainService {

    @Override
    public RequestCreatedEvent request(Request request) {
        request.initialize();
        log.info("Request[id: {}] is initialized.", request.getId().getValue());
        return new RequestCreatedEvent(request, ZonedDateTime.now());
    }
}
