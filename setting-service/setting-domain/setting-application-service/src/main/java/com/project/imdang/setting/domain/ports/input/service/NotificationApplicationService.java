package com.project.imdang.setting.domain.ports.input.service;

import com.project.imdang.setting.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.domain.dto.NotificationResult;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface NotificationApplicationService {
    Boolean checkNewNotification(UUID memberId);
    Page<NotificationResult> listNotification(ListNotificationQuery listNotificationQuery);
    void updateNotificationAsChecked(UUID memberId);
}
