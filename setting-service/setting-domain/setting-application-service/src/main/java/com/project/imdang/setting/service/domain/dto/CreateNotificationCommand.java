package com.project.imdang.setting.service.domain.dto;

import com.project.imdang.setting.service.domain.valueobject.NotificationCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CreateNotificationCommand {
    private NotificationCategory category;
    private String message;
}
