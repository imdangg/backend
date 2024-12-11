package com.project.imdang.setting.service.domain.handler;

import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.mapper.NotificationDataMapper;
import com.project.imdang.setting.service.domain.ports.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListNotificationCommandHandler {

    private final NotificationRepository notificationRepository;
    private final NotificationDataMapper notificationDataMapper;

    @Transactional(readOnly = true)
    public Page<NotificationResponse> listUncheckedNotification(ListNotificationQuery listNotificationQuery) {
        Sort sort = Sort.by(Sort.Direction.valueOf(listNotificationQuery.getDirection()), listNotificationQuery.getProperties());
        PageRequest pageRequest = PageRequest.of(listNotificationQuery.getPageNumber(), listNotificationQuery.getPageSize(), sort);
        return notificationRepository.findAllByIsChecked(false, pageRequest)
                .map(notificationDataMapper::notificationToNotificationResponse);
    }
}
