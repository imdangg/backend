package com.project.imdang.setting.service.domain.handler;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.dto.ListNotificationQuery;
import com.project.imdang.setting.service.domain.dto.NotificationResponse;
import com.project.imdang.setting.service.domain.entity.Notification;
import com.project.imdang.setting.service.domain.mapper.NotificationDataMapper;
import com.project.imdang.setting.service.domain.ports.output.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListUncheckNotificationCommandHandler {

    private final NotificationRepository notificationRepository;
    private final NotificationDataMapper notificationDataMapper;

    @Transactional(readOnly = true)
    public List<NotificationResponse> listUncheckNotification(UUID _receiverId) {
        MemberId receiverId = new MemberId(_receiverId);
        return notificationRepository.findAllByReciverIdAndIsChecked(receiverId, false)
                .stream().map(notificationDataMapper::notificationToNotificationResponse)
                .collect(Collectors.toList());
    }
}
