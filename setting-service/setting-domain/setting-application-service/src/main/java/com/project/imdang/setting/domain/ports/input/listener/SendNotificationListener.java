package com.project.imdang.setting.domain.ports.input.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.common.domain.event.entry.EventEntry;
import com.project.imdang.common.domain.event.entry.EventListener;
import com.project.imdang.common.domain.exception.DomainException;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.NotificationCategory;
import com.project.imdang.member.domain.client.MemberData;
import com.project.imdang.member.domain.client.MemberDataResolver;
import com.project.imdang.member.domain.client.exception.MemberNotFoundException;
import com.project.imdang.setting.domain.dto.CreateNotificationCommand;
import com.project.imdang.setting.domain.handler.CreateNotificationCommandHandler;
import com.project.imdang.setting.domain.ports.output.sender.NotificationRequest;
import com.project.imdang.setting.domain.ports.output.sender.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class SendNotificationListener implements EventListener {

    private final CreateNotificationCommandHandler createNotificationCommandHandler;
    private final MemberDataResolver memberResolver;

    private final NotificationSender notificationSender;
    private final ObjectMapper objectMapper;

    @Override
    public void process(EventEntry eventEntry) {

        NotificationCategory category = NotificationCategory.getType(eventEntry.getType());
        final UUID receiverId = getReceiverId(eventEntry.getPayload());

        MemberId memberId = new MemberId(receiverId);
        MemberData receiver = memberResolver.resolve(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        final String message = String.format(category.getNotificationContent(), receiver.getNickname());

        // 1. 알림 생성
        CreateNotificationCommand createNotificationCommand
                = new CreateNotificationCommand(category, memberId, message);
        createNotificationCommandHandler.createNotification(createNotificationCommand);

        // 2. 푸시 알림 생성
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .receiverId(receiverId)
//                .category(category)
                .title(category.getTitle())
                .body(category.getPushNotificationContent())
                .createdAt(ZonedDateTime.now())
                .build();
        notificationSender.send(notificationRequest);
    }

    private UUID getReceiverId(String payload) {
        try {
            JsonNode jsonNode = objectMapper.readTree(payload);
            String receiverId = jsonNode
                    .get("exchangeRequest")
                    .get("requestMemberId")
                    .get("value")
                    .asText();
            return UUID.fromString(receiverId);
        } catch (JsonProcessingException e) {
            // TODO - 예외 처리
            throw new DomainException("JSON ERROR!");
        }
    }
}
