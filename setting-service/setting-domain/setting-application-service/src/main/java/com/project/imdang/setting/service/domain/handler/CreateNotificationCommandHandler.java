package com.project.imdang.setting.service.domain.handler;

import com.project.imdang.setting.service.domain.NotificationDomainService;
import com.project.imdang.setting.service.domain.dto.CreateNotificationCommand;
import com.project.imdang.setting.service.domain.entity.Notification;
import com.project.imdang.setting.service.domain.mapper.NotificationDataMapper;
import com.project.imdang.setting.service.domain.ports.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateNotificationCommandHandler {

//    private final NotificationDomainService notificationDomainService;
    private final NotificationRepository notificationRepository;
    private final NotificationDataMapper notificationDataMapper;

    @Transactional
    public void createNotification(CreateNotificationCommand createNotificationCommand) {
        Notification notification = notificationDataMapper.createNotificationCommandToNotification(createNotificationCommand);
//        notificationDomainService.createNotification(notification);
    }
}
