package com.project.imdang.common.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum NotificationCategory {
    RECOMMENDED("추천",
            "RecommendedEvent",
            "내 인사이트가 추천받았어요\uD83E\uDD13 지금 눌러서 확인하기.",
            "%s님이 인사이트를 추천했어요.");

    private final String title;
    private final String type;
    private final String pushNotificationContent;
    private final String notificationContent;

    public static NotificationCategory getType(String type) {
        return Arrays.stream(NotificationCategory.values())
                .filter(t -> t.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not exist NotificationType"));
    }
}
