package com.project.imdang.setting.service.domain.ports.output.repository;

import com.project.imdang.setting.service.domain.entity.Notification;

import java.util.List;

public interface NotificationRepository {

    List<Notification> findAll();
    List<Notification> findByChecked(Boolean checked);
    Notification createNotification(Notification notification);
}
