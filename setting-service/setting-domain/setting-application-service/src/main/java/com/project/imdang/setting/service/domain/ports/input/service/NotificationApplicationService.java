package com.project.imdang.setting.service.domain.ports.input.service;

import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface NotificationApplicationService {
    Boolean checkNewNotification(UUID memberId);
    Page<NotificationResponse> listNotification(ListNotificationQuery listNotificationQuery);
    void updateNotificationAsChecked(UUID memberId);
}
