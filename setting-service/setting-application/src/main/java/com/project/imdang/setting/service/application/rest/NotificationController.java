package com.project.imdang.setting.service.application.rest;

import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.ports.input.service.NotificationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@Tag(name = "NotificationController", description = "알림 API")
@RequestMapping("/notifications")
@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final NotificationApplicationService notificationApplicationService;

    @Operation(description = "읽지 않은 알림 유무 조회 API")
    @ApiResponse(responseCode = "200", description = "읽지 않은 알림 유무 조회 성공")
    @GetMapping("/unchecked")
    public ResponseEntity<Boolean> check(@AuthenticationPrincipal UUID memberId) {
        Boolean isNew = notificationApplicationService.checkNewNotification(memberId);
        return ResponseEntity.ok(isNew);
    }

    @Operation(description = "알림 리스트 조회 API")
    @ApiResponse(responseCode = "200", description = "알림 리스트 조회 성공")
    @GetMapping
    public ResponseEntity<Page<NotificationResponse>> list(@AuthenticationPrincipal UUID memberId,
                                                                  @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                                  @RequestParam(name = "direction", defaultValue = "DESC") String direction,
                                                                  @RequestParam(name = "properties", defaultValue = "created_at") String[] properties) {

        notificationApplicationService.updateNotificationAsChecked(memberId);
        log.info("Member[id:{}] new notification is checked", memberId);

        ListNotificationQuery listNotificationQuery = ListNotificationQuery.builder()
                .receiverId(memberId)
                .isChecked(true)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .direction(direction)
                .properties(properties)
                .build();
        Page<NotificationResponse> paged = notificationApplicationService.listNotification(listNotificationQuery);
        log.info("Member[id:{}] notification list is retrieved", memberId);
        return ResponseEntity.ok(paged);
    }
}
