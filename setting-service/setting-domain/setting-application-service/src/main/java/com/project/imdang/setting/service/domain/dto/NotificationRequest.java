package com.project.imdang.setting.service.domain.dto;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.valueobject.NotificationCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NotificationRequest {
    private UUID memberId;
    private NotificationCategory category;
    private String title;
    private String body;
    private ZonedDateTime createdAt;
}
