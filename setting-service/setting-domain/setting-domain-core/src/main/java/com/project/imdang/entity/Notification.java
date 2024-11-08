package com.project.imdang.entity;

import com.project.imdang.valueobject.NotificationCategory;
import com.project.imdang.valueobject.NotificationId;

import java.time.ZonedDateTime;

public class Notification {
    private NotificationId id;

    private NotificationCategory category;
//    private String title;

    // TODO - 알림 클릭 시 해당 페이지로 이동 
    // NotificationContents 클래스
    private String contents;
    private ZonedDateTime createdAt;

    private ZonedDateTime checkedAt;
}
