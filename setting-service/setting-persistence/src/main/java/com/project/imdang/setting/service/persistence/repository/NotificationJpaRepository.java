package com.project.imdang.setting.service.persistence.repository;

import com.project.imdang.setting.service.persistence.entity.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long> {
    Page<NotificationEntity> findAllByReceiverIdAndIsCheckedAndCreatedAtAfter(UUID receiverId, Boolean checked, ZonedDateTime time, PageRequest pageRequest);
    List<NotificationEntity> findAllByReceiverIdAndIsChecked(UUID memberId, Boolean checked);
}
