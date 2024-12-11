package com.project.imdang.setting.service.persistence.notification.adapter;

import com.project.imdang.setting.service.domain.entity.Notification;
import com.project.imdang.setting.service.domain.ports.output.repository.NotificationRepository;
import com.project.imdang.setting.service.persistence.notification.entity.NotificationEntity;
import com.project.imdang.setting.service.persistence.notification.mapper.NotificationPersistenceMapper;
import com.project.imdang.setting.service.persistence.notification.repository.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;
    private final NotificationPersistenceMapper notificationPersistenceMapper;

    @Override
    public List<Notification> findAllByIds(List<Long> notificationIds) {
        return notificationJpaRepository.findAllById(notificationIds).stream()
                .map(notificationPersistenceMapper::notificationEntityToNotification)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Notification> findAllByIsChecked(Boolean checked, PageRequest pageRequest) {
        return notificationJpaRepository.findAllByIsChecked(checked, pageRequest)
                .map(notificationPersistenceMapper::notificationEntityToNotification);
    }

    @Override
    public Notification save(Notification notification) {
        NotificationEntity notificationEntity = notificationPersistenceMapper.notificationToNotificationEntity(notification);
        NotificationEntity saved = notificationJpaRepository.save(notificationEntity);
        return notificationPersistenceMapper.notificationEntityToNotification(saved);
    }
}
