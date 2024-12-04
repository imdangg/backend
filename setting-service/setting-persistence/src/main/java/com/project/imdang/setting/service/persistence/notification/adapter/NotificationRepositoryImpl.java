package com.project.imdang.setting.service.persistence.notification.adapter;

import com.project.imdang.setting.service.domain.entity.Notification;
import com.project.imdang.setting.service.domain.ports.output.repository.NotificationRepository;
import com.project.imdang.setting.service.persistence.notification.entity.NotificationEntity;
import com.project.imdang.setting.service.persistence.notification.mapper.NotificationPersistenceMapper;
import com.project.imdang.setting.service.persistence.notification.repository.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;
    private final NotificationPersistenceMapper notificationPersistenceMapper;

    @Override
    public List<Notification> findAll() {
        return null;
    }

    @Override
    public List<Notification> findByChecked(Boolean checked) {
        return notificationJpaRepository.findByChecked(checked).stream()
                .map(notificationPersistenceMapper::notificationEntityToNotification)
                .collect(Collectors.toList());
    }

    @Override
    public Notification createNotification(Notification notification) {
        NotificationEntity notificationEntity = notificationPersistenceMapper.notificationToNotificationEntity(notification);
        NotificationEntity saved = notificationJpaRepository.save(notificationEntity);
        return notificationPersistenceMapper.notificationEntityToNotification(saved);
    }
}
