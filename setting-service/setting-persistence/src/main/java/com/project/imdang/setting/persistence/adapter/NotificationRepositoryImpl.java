package com.project.imdang.setting.persistence.adapter;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.setting.domain.entity.Notification;
import com.project.imdang.setting.domain.ports.output.repository.NotificationRepository;
import com.project.imdang.setting.persistence.repository.NotificationJpaRepository;
import com.project.imdang.setting.persistence.entity.NotificationEntity;
import com.project.imdang.setting.persistence.mapper.NotificationPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;
    private final NotificationPersistenceMapper notificationPersistenceMapper;

    @Override
    public List<Notification> findAllByReceiverIdAndIsChecked(MemberId memberId, Boolean isChecked) {
        return notificationJpaRepository.findAllByReceiverIdAndIsChecked(memberId.getValue(), isChecked).stream()
                .map(notificationPersistenceMapper::notificationEntityToNotification)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Notification> findAllByReceiverIdAndIsCheckedAndCreatedAt(MemberId receiverId, Boolean checked, ZonedDateTime time, PageRequest pageRequest) {
        return notificationJpaRepository.findAllByReceiverIdAndIsCheckedAndCreatedAtAfter(receiverId.getValue(),checked,time,pageRequest)
                .map(notificationPersistenceMapper::notificationEntityToNotification);
    }

    @Override
    public Notification save(Notification notification) {
        NotificationEntity notificationEntity = notificationPersistenceMapper.notificationToNotificationEntity(notification);
        NotificationEntity saved = notificationJpaRepository.save(notificationEntity);
        return notificationPersistenceMapper.notificationEntityToNotification(saved);
    }
}
