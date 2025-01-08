package com.project.imdang.setting.service.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationCategory {
    // 다른 사람이 나에게 인사이트 교환을 요청했을 경우
    REQUESTED("교환 요청", "누군가가 내 인사이트에 교환을 요청했어요!"),
    // 내가 교환을 요청했을 때 상대방이 수락하는 경우
    ACCEPTED("수락", "요청하신 인사이트 교환이 수락됐어요!"),
    // 내가 교환을 요청했을 때 상대방이 거절하는 경우
    REJECTED("거절", "요청하신 인사이트 교환이 거절됐어요!");

    private final String title;
    private final String content;

}
