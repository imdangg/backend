package com.project.imdang.setting.domain.ports.output.sender;

public interface NotificationSender {
    void send(NotificationRequest notificationRequest);
}
