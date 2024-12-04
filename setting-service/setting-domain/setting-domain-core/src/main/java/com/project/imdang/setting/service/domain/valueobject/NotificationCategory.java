package com.project.imdang.setting.service.domain.valueobject;

public enum NotificationCategory {
    // 다른 사람이 나에게 인사이트 교환을 요청했을 경우
    REQUESTED, 
    // 내가 교환을 요청했을 때 상대방이 수락하는 경우
    ACCEPTED, 
    // 내가 교환을 요청했을 때 상대방이 거절하는 경우
    REJECTED
}
