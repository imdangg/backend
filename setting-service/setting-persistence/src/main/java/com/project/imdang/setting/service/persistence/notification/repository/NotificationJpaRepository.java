package com.project.imdang.setting.service.persistence.notification.repository;

import com.project.imdang.setting.service.domain.ports.output.repository.NotificationRepository;
import com.project.imdang.setting.service.persistence.notification.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findByChecked(Boolean checked);
}
