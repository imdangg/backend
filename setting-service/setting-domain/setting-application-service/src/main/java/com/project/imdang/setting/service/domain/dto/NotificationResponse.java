package com.project.imdang.setting.service.domain.dto;

import com.project.imdang.setting.service.domain.valueobject.NotificationCategory;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class NotificationResponse {
    @Schema(description = "알림 ID")
    private Long notificationId;
    @Schema(description = "알림 종류")
    private NotificationCategory category;
    @Schema(description = "알림 메세지")
    private String message;
    @Schema(description = "알림 생성 날짜")
    private ZonedDateTime createdAt;
}
