package com.project.imdang.setting.service.application.rest;

import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.ports.input.service.NotificationApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/notifications")
@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final NotificationApplicationService notificationApplicationService;

    // 읽지 않은 알림 리스트 조회 (+ '읽음' 상태로 변경)
    // notifications?checked=false
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> list(@ModelAttribute ListNotificationQuery listNotificationQuery) {
        List<NotificationResponse> notificationResponses = notificationApplicationService.listNotification(listNotificationQuery);
        return ResponseEntity.ok(notificationResponses);
    }
}
