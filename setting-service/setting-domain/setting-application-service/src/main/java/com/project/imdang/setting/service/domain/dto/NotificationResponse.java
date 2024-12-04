package com.project.imdang.setting.service.domain.dto;

import com.project.imdang.setting.service.domain.valueobject.NotificationCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationResponse {
    private Long notificationId;
    private NotificationCategory category;
    private String message;
}
