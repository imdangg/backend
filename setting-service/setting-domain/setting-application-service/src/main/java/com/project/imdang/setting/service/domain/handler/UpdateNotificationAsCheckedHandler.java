package com.project.imdang.setting.service.domain.handler;

import com.project.imdang.setting.service.domain.NotificationDomainService;
import com.project.imdang.setting.service.domain.entity.Notification;
import com.project.imdang.setting.service.domain.exception.NotificationDomainException;
import com.project.imdang.setting.service.domain.ports.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateNotificationAsCheckedHandler {

    private final NotificationDomainService notificationDomainService;
    private final NotificationRepository notificationRepository;

    @Transactional
    public void updateAsChecked(List<Long> notificationIds) {
        List<Notification> notifications = notificationRepository.findAllByIds(notificationIds);

        // TODO - CHECK : 일괄 업데이트가 빠를텐데?
//        notificationRepository.updateIsChecked(notificationIds, Boolean.TRUE);
        notifications.forEach(notification -> {
            notificationDomainService.updateNotificationAsChecked(notification);
            log.info("Notification[id: {}] is updated as checked.", notification.getId().getValue());
            save(notification);
        });
    }

    // TODO - ASYNC
    private void save(Notification notification) {
        Notification saved = notificationRepository.save(notification);
        if (saved == null) {
            String errorMessage = "Could not save notification!";
            log.error(errorMessage);
            throw new NotificationDomainException(errorMessage);
        }
        log.info("Notification[id: {}] is saved.", saved.getId().getValue());
    }
}
