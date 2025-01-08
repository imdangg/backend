package com.project.imdang.setting.service.domain;

import com.project.imdang.setting.service.domain.dto.CreateNotificationCommand;
import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationRequest;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.handler.CreateNotificationCommandHandler;
import com.project.imdang.setting.service.domain.handler.ListNotificationCommandHandler;
import com.project.imdang.setting.service.domain.handler.SendNotificationHandler;
import com.project.imdang.setting.service.domain.handler.UpdateNotificationAsCheckedHandler;
import com.project.imdang.setting.service.domain.ports.input.service.NotificationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@RequiredArgsConstructor
@Service
public class NotificationApplicationServiceImpl implements NotificationApplicationService {

    private final ListNotificationCommandHandler listNotificationCommandHandler;
    private final CreateNotificationCommandHandler createNotificationCommandHandler;
    private final UpdateNotificationAsCheckedHandler updateNotificationAsCheckedHandler;
    private final SendNotificationHandler sendNotificationHandler;

    @Override
    public Page<NotificationResponse> listUncheckedNotification(ListNotificationQuery listNotificationQuery) {
        return listNotificationCommandHandler.listUncheckedNotification(listNotificationQuery);
    }

    @Override
    public void createNotification(CreateNotificationCommand createNotificationCommand) {
        createNotificationCommandHandler.createNotification(createNotificationCommand);
    }

    // 조회 API 실행 시
    // TODO : Async 처리
    @Override
    public void updateNotificationAsChecked(List<Long> notificationIds) {
        updateNotificationAsCheckedHandler.updateAsChecked(notificationIds);
    }

    @Override
    public void sendNotification(NotificationRequest notificationRequest) {
        sendNotificationHandler.send(notificationRequest);
    }

    // 조회 API 실행 시
    // TODO : Async 처리
//    @Override
//    public void updateNotificationAsChecked(Long notificationId) {
//        updateNotificationAsCheckedHandler.updateAsChecked(notificationId);
//    }
}
