package com.project.imdang.setting.service.persistence.repository;

import com.project.imdang.setting.service.persistence.entity.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long> {

    @Query(value = "SELECT * FROM notification n  WHERE n.receiver_id = :receiverId AND n.is_checked = :checked AND n.created_at > :time \n-- #pageRequest\n",
            countQuery = "SELECT count(*) FROM notification n  WHERE n.receiver_id = :receiverId AND n.is_checked = :checked AND n.created_at > :time",
            nativeQuery = true)
    Page<NotificationEntity> findAllByReceiverIdAndIsCheckedAndCreatedAtAfter(UUID receiverId, Boolean checked, ZonedDateTime time, PageRequest pageRequest);
    List<NotificationEntity> findAllByReceiverIdAndIsChecked(UUID memberId, Boolean checked);
}
