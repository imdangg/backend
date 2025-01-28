package com.project.imdang.setting.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    List<Notification> findAllByReciverIdAndIsChecked(MemberId memberId, Boolean isChecked);
    List<Notification> findAllByIds(List<Long> notificationIds);
    Page<Notification> findAllByReceiverIdAndIsCheckedAndCreatedAt(MemberId memberId, Boolean isChecked, ZonedDateTime time, PageRequest pageRequest);
    Notification save(Notification notification);

//    void updateIsChecked(List<Long> notificationIds, Boolean isChecked);
}
