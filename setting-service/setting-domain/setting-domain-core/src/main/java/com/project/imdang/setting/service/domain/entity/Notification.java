package com.project.imdang.setting.service.domain.entity;

import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.setting.service.domain.valueobject.NotificationCategory;
import com.project.imdang.setting.service.domain.valueobject.NotificationId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class Notification extends AggregateRoot<NotificationId> {
// TODO - CHECK : AggregateRoot vs BaseEntity

    // todo - receiver
    private NotificationCategory category;
//    private String title;

    // TODO - 알림 클릭 시 해당 페이지로 이동
    // NotificationContents 클래스
    private String message;
    private ZonedDateTime createdAt;

    private Boolean checked;
    private ZonedDateTime checkedAt;

    @Builder
    public Notification(NotificationId id, NotificationCategory category, String message, ZonedDateTime createdAt, Boolean checked, ZonedDateTime checkedAt) {
        setId(id);
        this.category = category;
        this.message = message;
        this.createdAt = createdAt;
        this.checked = checked;
        this.checkedAt = checkedAt;
    }
/*
    public static Notification createNewNotification(NotificationCategory category, String message) {
        return Notification.builder()
                .category(category)
                .message(message)
                .createdAt(ZonedDateTime.now())
                .build();
    }*/

    public void check() {
        if (Boolean.TRUE.equals(this.checked)) {
//            throw new NotificationDomainException();
        }
        this.checked = Boolean.TRUE;
        this.checkedAt = ZonedDateTime.now();
    }
}
