package com.project.imdang.setting.service.domain.dto;

import com.project.imdang.setting.service.domain.valueobject.NotificationCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateNotificationCommand {
    private NotificationCategory category;
    private UUID receiverId;
    private String message;
}
