package com.project.imdang.setting.persistence.repository;

import com.project.imdang.setting.persistence.entity.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long> {

    @Query(value = "select n.* from notification n where n.receiver_id = :receiverId and n.is_checked = :checked and n.created_at > :time",
            countQuery = "select count(*) from notification n where n.receiver_id = :receiverId and n.is_checked = :checked and n.created_at > :time",
            nativeQuery = true)
    Page<NotificationEntity> findAllByReceiverIdAndIsCheckedAndCreatedAtAfter(@Param("receiverId") String receiverId, @Param("checked") Boolean checked, @Param("time") ZonedDateTime time, PageRequest pageRequest);
    List<NotificationEntity> findAllByReceiverIdAndIsChecked(UUID memberId, Boolean checked);
}
