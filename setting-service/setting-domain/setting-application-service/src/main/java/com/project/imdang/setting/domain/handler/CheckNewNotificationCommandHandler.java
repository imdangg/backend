package com.project.imdang.setting.domain.handler;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.setting.domain.entity.Notification;
import com.project.imdang.setting.domain.ports.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CheckNewNotificationCommandHandler {

    private final NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    public Boolean checkNewNotification(MemberId receiverId) {
        List<Notification> notifications = notificationRepository.findAllByReceiverIdAndIsChecked(receiverId, Boolean.FALSE);
        return !notifications.isEmpty();
    }
}
