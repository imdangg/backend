package com.project.imdang.setting.application.rest;

import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.setting.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.domain.dto.NotificationResult;
import com.project.imdang.setting.domain.ports.input.service.NotificationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "읽지 않은 알림 유무 조회 성공")
    })
    @GetMapping("/unchecked")
    public ApiResponse<Boolean> check(@AuthenticationPrincipal UUID memberId) {
        Boolean isNew = notificationApplicationService.checkNewNotification(new MemberId(memberId));
        return ApiResponse.success(isNew);
    }

    @Operation(description = "알림 리스트 조회 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "알림 리스트 조회 성공")
    })
    @GetMapping
    public ApiResponse<Page<NotificationResult>> list(@AuthenticationPrincipal UUID memberId,
                                                         @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                         @RequestParam(name = "direction", defaultValue = "DESC") String direction,
                                                         @RequestParam(name = "properties", defaultValue = "created_at") String[] properties) {
        // TODO - CHECK
        notificationApplicationService.updateNotificationAsChecked(new MemberId(memberId));
        log.info("New notifications of Member[id: {}] is checked.", memberId);

        ListNotificationQuery listNotificationQuery = ListNotificationQuery.builder()
                .receiverId(new MemberId(memberId))
                // TODO - CHECK
                .isChecked(true)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .direction(direction)
                .properties(properties)
                .build();
        Page<NotificationResult> notifications = notificationApplicationService.listNotification(listNotificationQuery);
        log.info("Notifications of Member[id: {}] are retrieved.", memberId);
        return ApiResponse.success(notifications);
    }
}
