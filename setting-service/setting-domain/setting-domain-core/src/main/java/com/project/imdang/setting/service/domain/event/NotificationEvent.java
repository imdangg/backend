package com.project.imdang.setting.service.domain.event;

import com.project.imdang.domain.event.DomainEvent;
import com.project.imdang.setting.service.domain.entity.Notification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
public abstract class NotificationEvent implements DomainEvent<Notification> {
    private final Notification notification;
    private final ZonedDateTime createdAt;
}
