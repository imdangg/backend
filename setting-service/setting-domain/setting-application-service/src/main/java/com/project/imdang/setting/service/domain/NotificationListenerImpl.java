package com.project.imdang.setting.service.domain;

import com.project.imdang.setting.service.domain.dto.NotificationRequest;
import com.project.imdang.setting.service.domain.entity.Notification;
import com.project.imdang.setting.service.domain.event.NotificationCreatedEvent;
import com.project.imdang.setting.service.domain.ports.input.listener.NotificationListener;
import com.project.imdang.setting.service.domain.ports.input.service.NotificationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListenerImpl implements NotificationListener {

    private final NotificationApplicationService notificationApplicationService;

    //추후 Executor 설정
    @Override
    @Async
    @EventListener
    public void notificationEvent(NotificationCreatedEvent notificationCreatedEvent) {
        // notification 요청 생성
        Notification notification = notificationCreatedEvent.getNotification();
        NotificationRequest notificationRequest = new NotificationRequest(notification.getReceiverId().getValue(), notification.getCategory(), notification.getCategory().getTitle(), notification.getCategory().getContent(), notification.getCreatedAt());
        // 알림 전송
        notificationApplicationService.sendNotification(notificationRequest);
    }
}
