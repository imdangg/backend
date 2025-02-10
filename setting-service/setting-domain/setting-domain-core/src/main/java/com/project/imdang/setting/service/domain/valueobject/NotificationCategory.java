package com.project.imdang.setting.service.domain.valueobject;

import com.project.imdang.setting.service.domain.exception.NotificationDomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum NotificationCategory {
    // 다른 사람이 나에게 인사이트 교환을 요청했을 경우
    REQUESTED("교환 요청", "ExchangeRequestCreatedEvent","누군가가 내 인사이트에 교환을 요청했어요/uD83E/uDD13 지금 눌러서 확인하기.",
            "%s님이 인사이트 교환을 요청했어요.\n인사이트 확인 후 수락 및 거절을 선택해주세요."),
    // 내가 교환을 요청했을 때 상대방이 수락하는 경우
    ACCEPTED("교환 수락", "ExchangeRequestAcceptedEvent","요청하신 인사이트 교환이 수락됐어요/uD83E/uDD73 지금 눌러서 확인하기.",
            "%s님이 인사이트 교환을 수락했어요.\n교환한 인사이트를 보관함에서 확인해보세요."),
    // 내가 교환을 요청했을 때 상대방이 거절하는 경우
    REJECTED("교환 거절", "ExchangeRequestRejectedEvent", "요청하신 인사이트 교환이 거절됐어요/uD83E/uDE22 지금 눌러서 확인하기.",
            "%s님이 인사이트 교환을 거절했어요.");

    private final String title;
    private final String type;
    private final String pushNotificationContent;
    private final String notificationContent;

    public static NotificationCategory getType(String type) {
        return Arrays.stream(NotificationCategory.values())
                .filter(t -> t.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new NotificationDomainException("Not exist NotificationType"));
    }
}