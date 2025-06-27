package com.project.imdang.setting.domain.handler;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.setting.domain.NotificationDomainService;
import com.project.imdang.setting.domain.entity.Notification;
import com.project.imdang.setting.domain.exception.NotificationDomainException;
import com.project.imdang.setting.domain.ports.output.repository.NotificationRepository;
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
    public Boolean updateAsChecked(MemberId receiverId) {
        List<Notification> uncheckedNotification = getUncheckedNotification(receiverId);
//        List<Notification> notifications = notificationRepository.findAllByIds(notificationIds);

        // TODO - CHECK : 일괄 업데이트가 빠를텐데?
//        notificationRepository.updateIsChecked(notificationIds, Boolean.TRUE);
        uncheckedNotification.forEach(notification -> {
            notificationDomainService.updateNotificationAsChecked(notification);
            save(notification);
        });
        return true;
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

    private List<Notification> getUncheckedNotification(MemberId receiverId) {
       return notificationRepository.findAllByReceiverIdAndIsChecked(receiverId, Boolean.FALSE);
    }
}
