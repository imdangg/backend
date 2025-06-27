package com.project.imdang.setting.domain;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.setting.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.domain.dto.NotificationResult;
import com.project.imdang.setting.domain.handler.CheckNewNotificationCommandHandler;
import com.project.imdang.setting.domain.handler.ListNotificationCommandHandler;
import com.project.imdang.setting.domain.handler.UpdateNotificationAsCheckedHandler;
import com.project.imdang.setting.domain.ports.input.service.NotificationApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationApplicationServiceImpl implements NotificationApplicationService {

    private final CheckNewNotificationCommandHandler checkNewNotificationCommandHandler;
    private final ListNotificationCommandHandler listNotificationCommandHandler;
    private final UpdateNotificationAsCheckedHandler updateNotificationAsCheckedHandler;

    @Override
    public Boolean checkNewNotification(MemberId memberId) {
        return checkNewNotificationCommandHandler.checkNewNotification(memberId);
    }

    @Override
    public Page<NotificationResult> listNotification(ListNotificationQuery listNotificationQuery) {
        return listNotificationCommandHandler.listNotification(listNotificationQuery);
    }

    // 조회 API 실행 시
    // TODO : Async 처리
    @Override
    public Boolean updateNotificationAsChecked(MemberId memberId) {
        Boolean updated = updateNotificationAsCheckedHandler.updateAsChecked(memberId);
        log.info("Notifications of member[id: {}] is updated as checked.", memberId.getValue());
        return updated;
    }
}
