package com.project.imdang.setting.domain.mapper;

import com.project.imdang.setting.domain.dto.CreateNotificationCommand;
import com.project.imdang.setting.domain.dto.NotificationResult;
import com.project.imdang.setting.domain.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationDataMapper {

    public Notification createNotificationCommandToNotification(CreateNotificationCommand createNotificationCommand) {
        return Notification.builder()
                .category(createNotificationCommand.getCategory())
                .receiverId(createNotificationCommand.getReceiverId())
                .message(createNotificationCommand.getMessage())
                .isChecked(Boolean.FALSE)
                .build();
    }

    public NotificationResult notificationToNotificationResult(Notification notification) {
        return NotificationResult.builder()
                .notificationId(notification.getId().getValue())
                .category(notification.getCategory())
                .message(notification.getMessage())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}
