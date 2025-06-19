package com.project.imdang.setting.domain.ports.output.repository;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.setting.domain.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.ZonedDateTime;
import java.util.List;

public interface NotificationRepository {
    List<Notification> findAllByReceiverIdAndIsChecked(MemberId memberId, Boolean isChecked);
    Page<Notification> findAllByReceiverIdAndIsCheckedAndCreatedAt(MemberId memberId, Boolean isChecked, ZonedDateTime time, PageRequest pageRequest);
    Notification save(Notification notification);
}
