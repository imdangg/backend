package com.project.imdang.setting.service.domain.handler;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.ports.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class CheckNewNotificationCommandHandler {

    private final NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    public Boolean checkNewNotification(UUID _receiverId) {
        MemberId receiverId = new MemberId(_receiverId);
        return !notificationRepository.findAllByReceiverIdAndIsChecked(receiverId, Boolean.FALSE).isEmpty();
    }
}
