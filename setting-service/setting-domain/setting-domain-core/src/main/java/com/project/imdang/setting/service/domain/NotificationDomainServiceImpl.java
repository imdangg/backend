package com.project.imdang.setting.service.domain;

import com.project.imdang.setting.service.domain.entity.Notification;
import com.project.imdang.setting.service.domain.event.NotificationCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class NotificationDomainServiceImpl implements NotificationDomainService {

    @Override
    public NotificationCreatedEvent createNotification(Notification notification) {
        notification.initialize();
        log.info("Notification[id: {}] is created.", notification.getId().getValue());
        return new NotificationCreatedEvent(notification, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void updateNotificationAsChecked(Notification notification) {
        notification.check();
        log.info("Notification[id: {}] is checked.", notification.getId().getValue());
    }
}
