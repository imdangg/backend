package com.project.imdang.setting.service.persistence.notification.repository;

import com.project.imdang.setting.service.persistence.notification.entity.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long> {
    Page<NotificationEntity> findAllByIsChecked(Boolean checked, PageRequest pageRequest);
}
