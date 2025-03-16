package com.project.imdang.setting.service.domain;

import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.handler.CheckNewNotificationCommandHandler;
import com.project.imdang.setting.service.domain.handler.ListNotificationCommandHandler;
import com.project.imdang.setting.service.domain.handler.UpdateNotificationAsCheckedHandler;
import com.project.imdang.setting.service.domain.ports.input.service.NotificationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@RequiredArgsConstructor
@Service
public class NotificationApplicationServiceImpl implements NotificationApplicationService {

    private final CheckNewNotificationCommandHandler checkNewNotificationCommandHandler;
    private final ListNotificationCommandHandler listNotificationCommandHandler;
    private final UpdateNotificationAsCheckedHandler updateNotificationAsCheckedHandler;

    @Override
    public Boolean checkNewNotification(UUID memberId) {
        return checkNewNotificationCommandHandler.checkNewNotification(memberId);
    }

    @Override
    public Page<NotificationResponse> listNotification(ListNotificationQuery listNotificationQuery) {
        return listNotificationCommandHandler.listNotification(listNotificationQuery);
    }

    // 조회 API 실행 시
    // TODO : Async 처리
    @Override
    public void updateNotificationAsChecked(UUID memberId) {
        updateNotificationAsCheckedHandler.updateAsChecked(memberId);
    }
}
