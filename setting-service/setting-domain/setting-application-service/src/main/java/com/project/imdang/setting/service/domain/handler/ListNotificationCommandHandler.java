package com.project.imdang.setting.service.domain.handler;

import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.mapper.NotificationDataMapper;
import com.project.imdang.setting.service.domain.ports.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListNotificationCommandHandler {

    private final NotificationRepository notificationRepository;
    private final NotificationDataMapper notificationDataMapper;

    @Transactional(readOnly = true)
    public List<NotificationResponse> listNotification(ListNotificationQuery listNotificationQuery) {
        return null;
    }
}
