package com.project.imdang.setting.domain.ports.output.sender;

import com.project.imdang.setting.domain.exception.MessagingException;

public interface NotificationSender {
    void send(NotificationRequest notificationRequest) throws MessagingException;
}
