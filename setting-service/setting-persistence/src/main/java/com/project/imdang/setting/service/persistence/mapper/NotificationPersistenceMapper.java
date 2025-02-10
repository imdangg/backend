package com.project.imdang.setting.service.persistence.mapper;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.entity.Notification;
import com.project.imdang.setting.service.domain.valueobject.NotificationId;
import com.project.imdang.setting.service.persistence.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationPersistenceMapper {

    public NotificationEntity notificationToNotificationEntity(Notification notification) {
        return NotificationEntity.builder()
                .category(notification.getCategory())
                .message(notification.getMessage())
                .createdAt(notification.getCreatedAt())
                .isChecked(notification.getIsChecked())
                .receiverId(notification.getReceiverId().getValue())
                .checkedAt(notification.getCheckedAt())
                .build();
    }

    public Notification notificationEntityToNotification(NotificationEntity notificationEntity) {
        return Notification.builder()
                .id(new NotificationId(notificationEntity.getId()))
                .category(notificationEntity.getCategory())
                .message(notificationEntity.getMessage())
                .receiverId(new MemberId(notificationEntity.getReceiverId()))
                .createdAt(notificationEntity.getCreatedAt())
                .isChecked(notificationEntity.getIsChecked())
                .checkedAt(notificationEntity.getCheckedAt())
                .build();
    }
}
