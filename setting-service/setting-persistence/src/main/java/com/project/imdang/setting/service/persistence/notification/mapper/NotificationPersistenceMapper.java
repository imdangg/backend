package com.project.imdang.setting.service.persistence.notification.mapper;

import com.project.imdang.setting.service.domain.entity.Notification;
import com.project.imdang.setting.service.domain.valueobject.NotificationId;
import com.project.imdang.setting.service.persistence.notification.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationPersistenceMapper {
    public NotificationEntity notificationToNotificationEntity(Notification notification) {
        return NotificationEntity.builder()
                .id(notification.getId().getValue())
                .category(notification.getCategory())
                .message(notification.getMessage())
                .createdAt(notification.getCreatedAt())
                .isChecked(notification.getIsChecked())
                .checkedAt(notification.getCheckedAt())
                .build();
    }

    public Notification notificationEntityToNotification(NotificationEntity notificationEntity) {
        return Notification.builder()
                .id(new NotificationId(notificationEntity.getId()))
                .category(notificationEntity.getCategory())
                .message(notificationEntity.getMessage())
                .createdAt(notificationEntity.getCreatedAt())
                .isChecked(notificationEntity.getIsChecked())
                .checkedAt(notificationEntity.getCheckedAt())
                .build();
    }
}
