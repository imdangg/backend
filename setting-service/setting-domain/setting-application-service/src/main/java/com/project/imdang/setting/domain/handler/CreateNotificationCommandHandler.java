package com.project.imdang.setting.domain.handler;

import com.project.imdang.setting.domain.NotificationDomainService;
import com.project.imdang.setting.domain.dto.CreateNotificationCommand;
import com.project.imdang.setting.domain.entity.Notification;
import com.project.imdang.setting.domain.event.NotificationCreatedEvent;
import com.project.imdang.setting.domain.exception.NotificationDomainException;
import com.project.imdang.setting.domain.mapper.NotificationDataMapper;
import com.project.imdang.setting.domain.ports.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateNotificationCommandHandler {

    private final NotificationDomainService notificationDomainService;
    private final NotificationRepository notificationRepository;
    private final NotificationDataMapper notificationDataMapper;

    @Transactional
    public void createNotification(CreateNotificationCommand createNotificationCommand) {
        Notification notification = notificationDataMapper.createNotificationCommandToNotification(createNotificationCommand);
        NotificationCreatedEvent notificationCreatedEvent = notificationDomainService.createNotification(notification);
        Notification saved = save(notificationCreatedEvent.getNotification());
        log.info("Notification[id: {}] is created.", saved.getId().getValue());
    }

    private Notification save(Notification notification) {
        Notification saved = notificationRepository.save(notification);
        if (saved == null) {
            String errorMessage = "Could not save notification!";
            log.error(errorMessage);
            throw new NotificationDomainException(errorMessage);
        }
        log.info("Notification[id: {}] is saved.", saved.getId().getValue());
        return saved;
    }
}
