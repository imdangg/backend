package com.project.imdang.setting.service.domain.ports.output.repository;

import com.project.imdang.setting.service.domain.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
//    Optional<Notification> findById(Long notificationId);
    List<Notification> findAllByIds(List<Long> notificationIds);
    Page<Notification> findAllByIsChecked(Boolean isChecked, PageRequest pageRequest);
    Notification save(Notification notification);

//    void updateIsChecked(List<Long> notificationIds, Boolean isChecked);
}
