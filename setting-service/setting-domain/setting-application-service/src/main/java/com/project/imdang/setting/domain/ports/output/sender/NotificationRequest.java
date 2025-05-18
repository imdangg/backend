package com.project.imdang.setting.domain.ports.output.sender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class NotificationRequest {
    private UUID receiverId;
//    private NotificationCategory category;
    private String title;
    private String body;
    private ZonedDateTime createdAt;
}
