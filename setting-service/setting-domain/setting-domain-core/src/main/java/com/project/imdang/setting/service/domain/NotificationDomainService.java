package com.project.imdang.setting.service.domain;

import com.project.imdang.setting.service.domain.entity.Notification;
import com.project.imdang.setting.service.domain.event.NotificationCreatedEvent;

public interface NotificationDomainService {
    NotificationCreatedEvent createNotification(Notification notification);
    void updateNotificationAsChecked(Notification notification);
}
