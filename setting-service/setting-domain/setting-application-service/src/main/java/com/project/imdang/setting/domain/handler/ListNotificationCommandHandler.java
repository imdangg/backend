package com.project.imdang.setting.domain.handler;

import com.project.imdang.common.domain.utils.PagingUtils;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.setting.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.domain.dto.NotificationResult;
import com.project.imdang.setting.domain.mapper.NotificationDataMapper;
import com.project.imdang.setting.domain.ports.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListNotificationCommandHandler {

    private final NotificationRepository notificationRepository;
    private final NotificationDataMapper notificationDataMapper;

    @Transactional(readOnly = true)
    public Page<NotificationResult> listNotification(ListNotificationQuery listNotificationQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listNotificationQuery.getPageNumber(), listNotificationQuery.getPageSize(), listNotificationQuery.getDirection(), listNotificationQuery.getProperties());
        MemberId memberId = listNotificationQuery.getReceiverId();
        ZonedDateTime minusOneYear = ZonedDateTime.now().minusYears(1);
        return notificationRepository.findAllByReceiverIdAndIsCheckedAndCreatedAt(memberId, listNotificationQuery.isChecked(), minusOneYear, pageRequest)
                .map(notificationDataMapper::notificationToNotificationResult);
    }
}
