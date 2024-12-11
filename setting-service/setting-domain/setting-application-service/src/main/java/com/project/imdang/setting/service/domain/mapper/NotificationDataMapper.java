package com.project.imdang.setting.service.domain.mapper;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.dto.CreateNotificationCommand;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationDataMapper {

    public Notification createNotificationCommandToNotification(CreateNotificationCommand createNotificationCommand) {
        return Notification.builder()
                .category(createNotificationCommand.getCategory())
                .receiverId(new MemberId(createNotificationCommand.getReceiverId()))
                .message(createNotificationCommand.getMessage())
                .build();
    }

    public NotificationResponse notificationToNotificationResponse(Notification notification) {
        return NotificationResponse.builder()
                .notificationId(notification.getId().getValue())
                .category(notification.getCategory())
                .message(notification.getMessage())
                .build();
    }
}
