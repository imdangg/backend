package com.project.imdang.setting.service.domain.ports.input.service;

import com.project.imdang.setting.service.domain.dto.CreateNotificationCommand;
import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;

import java.util.List;

public interface NotificationApplicationService {
    List<NotificationResponse> listNotification(ListNotificationQuery listNotificationQuery);
    void createNotification(CreateNotificationCommand createNotificationCommand);
}
