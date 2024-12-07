package com.project.imdang.insight.service.domain.event;

import com.project.imdang.domain.event.DomainEvent;
import com.project.imdang.insight.service.domain.entity.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
public abstract class RequestEvent implements DomainEvent<Request> {
    private final Request request;
    private final ZonedDateTime createdAt;
}
