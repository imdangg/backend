package com.project.imdang.setting.domain;

import com.project.imdang.setting.domain.entity.Notification;
import com.project.imdang.setting.domain.event.NotificationCreatedEvent;

public interface NotificationDomainService {
    NotificationCreatedEvent createNotification(Notification notification);
    void updateNotificationAsChecked(Notification notification);
}
