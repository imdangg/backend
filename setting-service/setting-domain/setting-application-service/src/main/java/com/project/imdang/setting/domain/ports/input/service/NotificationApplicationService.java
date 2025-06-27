package com.project.imdang.setting.domain.ports.input.service;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.setting.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.domain.dto.NotificationResult;
import org.springframework.data.domain.Page;

public interface NotificationApplicationService {
    Boolean checkNewNotification(MemberId memberId);
    Page<NotificationResult> listNotification(ListNotificationQuery listNotificationQuery);
    Boolean updateNotificationAsChecked(MemberId memberId);
}
