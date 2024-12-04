package com.project.imdang.setting.service.domain;

import com.project.imdang.setting.service.domain.dto.CreateNotificationCommand;
import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.handler.CreateNotificationCommandHandler;
import com.project.imdang.setting.service.domain.handler.ListNotificationCommandHandler;
import com.project.imdang.setting.service.domain.ports.input.service.NotificationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@RequiredArgsConstructor
@Service
public class NotificationApplicationServiceImpl implements NotificationApplicationService {

    private final ListNotificationCommandHandler listNotificationCommandHandler;
    private final CreateNotificationCommandHandler createNotificationCommandHandler;

    @Override
    public List<NotificationResponse> listNotification(ListNotificationQuery listNotificationQuery) {
        return listNotificationCommandHandler.listNotification(listNotificationQuery);
    }

    @Override
    public void createNotification(CreateNotificationCommand createNotificationCommand) {
        createNotificationCommandHandler.createNotification(createNotificationCommand);
    }
}
