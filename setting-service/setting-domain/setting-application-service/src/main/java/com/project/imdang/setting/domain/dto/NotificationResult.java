package com.project.imdang.setting.domain.dto;

import com.project.imdang.common.domain.valueobject.NotificationCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationResult {
    private Long notificationId;
    private NotificationCategory category;
    private String message;
    private ZonedDateTime createdAt;
}
