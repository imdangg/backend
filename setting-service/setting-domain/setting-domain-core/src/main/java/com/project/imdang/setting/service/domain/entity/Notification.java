package com.project.imdang.setting.service.domain.entity;

import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.valueobject.NotificationCategory;
import com.project.imdang.setting.service.domain.valueobject.NotificationId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class Notification extends AggregateRoot<NotificationId> {
// TODO - CHECK : AggregateRoot vs BaseEntity

    private final NotificationCategory category;
    private final MemberId receiverId;

    // TODO - 알림 클릭 시 해당 페이지로 이동
    // NotificationContents 클래스
    private final String message;
    private ZonedDateTime createdAt;

    private Boolean isChecked;
    private ZonedDateTime checkedAt;

    @Builder
    public Notification(NotificationId id, NotificationCategory category, MemberId receiverId, String message, ZonedDateTime createdAt, Boolean isChecked, ZonedDateTime checkedAt) {
        setId(id);
        this.category = category;
        this.receiverId = receiverId;
        this.message = message;
        this.createdAt = createdAt;
        this.isChecked = isChecked;
        this.checkedAt = checkedAt;
    }

    public void initialize() {
        this.createdAt = ZonedDateTime.now();
        this.isChecked = Boolean.FALSE;
    }

    public void check() {
        if (Boolean.TRUE.equals(this.isChecked)) {
//            throw new NotificationDomainException();
        }
        this.isChecked = Boolean.TRUE;
        this.checkedAt = ZonedDateTime.now();
    }
}
