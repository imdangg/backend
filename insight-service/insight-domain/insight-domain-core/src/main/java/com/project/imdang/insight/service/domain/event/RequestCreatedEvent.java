package com.project.imdang.insight.service.domain.event;

import com.project.imdang.insight.service.domain.entity.Request;

import java.time.ZonedDateTime;

public class RequestCreatedEvent extends RequestEvent {

    public RequestCreatedEvent(Request request, ZonedDateTime createdAt) {
        super(request, createdAt);
    }
}
