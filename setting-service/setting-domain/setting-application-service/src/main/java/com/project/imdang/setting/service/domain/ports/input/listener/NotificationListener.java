package com.project.imdang.setting.service.domain.ports.input.listener;

import com.project.imdang.setting.service.domain.event.NotificationCreatedEvent;

public interface NotificationListener {

    void notificationEvent(NotificationCreatedEvent notificationCreatedEvent);
}
