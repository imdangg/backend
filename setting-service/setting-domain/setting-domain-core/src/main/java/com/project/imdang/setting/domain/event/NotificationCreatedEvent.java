package com.project.imdang.setting.domain.event;


import com.project.imdang.setting.domain.entity.Notification;

import java.time.ZonedDateTime;

public class NotificationCreatedEvent extends NotificationEvent {
    public NotificationCreatedEvent(Notification notification, ZonedDateTime createdAt) {
        super(notification, createdAt);
    }
}
