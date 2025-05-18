package com.project.imdang.setting.domain.event;

import com.project.imdang.common.domain.event.DomainEventMessage;
import com.project.imdang.setting.domain.entity.Notification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
public abstract class NotificationEvent implements DomainEventMessage {
    private final Notification notification;
    private final ZonedDateTime createdAt;
}
