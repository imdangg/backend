package com.project.imdang.setting.service.domain.ports.input.service;

import com.project.imdang.setting.service.domain.dto.CreateNotificationCommand;
import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationRequest;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface NotificationApplicationService {
    Boolean checkNewNotification(UUID memberId);
    List<NotificationResponse> listUncheckedNotification(UUID receiverId);
    Page<NotificationResponse> listNotification(ListNotificationQuery listNotificationQuery);
    void createNotification(CreateNotificationCommand createNotificationCommand);
    void updateNotificationAsChecked(List<Long> notificationIds);
//    void updateNotificationAsChecked(Long notificationId);
    void sendNotification(NotificationRequest notificationRequest);
}
