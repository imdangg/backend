package com.project.imdang.setting.service.application.rest;

import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.ports.input.service.NotificationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "NotificationController", description = "알림 API")
@RequestMapping("/notifications")
@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final NotificationApplicationService notificationApplicationService;

    // 읽지 않은 알림 리스트 조회 (+ '읽음' 상태로 변경)
    // notifications/unchecked
    @Operation(description = "읽지 않은 알림 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "읽지 않은 알림 목록 조회 성공")
    @GetMapping("/unchecked")
    public ResponseEntity<Page<NotificationResponse>> listUnchecked(@ModelAttribute ListNotificationQuery listNotificationQuery) {
        Page<NotificationResponse> paged = notificationApplicationService.listUncheckedNotification(listNotificationQuery);
        List<Long> notificationIds = paged.getContent().stream()
                .map(NotificationResponse::getNotificationId).toList();
        notificationApplicationService.updateNotificationAsChecked(notificationIds);
        return ResponseEntity.ok(paged);
    }
}
