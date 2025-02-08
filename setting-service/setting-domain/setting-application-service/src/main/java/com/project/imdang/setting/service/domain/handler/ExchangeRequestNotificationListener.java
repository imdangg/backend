package com.project.imdang.setting.service.domain.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.exception.DomainException;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.event.EventEntry;
import com.project.imdang.event.EventListener;
import com.project.imdang.setting.service.domain.dto.CreateNotificationCommand;
import com.project.imdang.setting.service.domain.dto.PushNotificationRequest;
import com.project.imdang.setting.service.domain.ports.output.lookup.SettingMemberLookup;
import com.project.imdang.setting.service.domain.valueobject.MemberInfo;
import com.project.imdang.setting.service.domain.valueobject.NotificationCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ExchangeRequestNotificationListener implements EventListener {

    private final CreateNotificationCommandHandler createNotificationCommandHandler;
    private final SendNotificationHandler sendNotificationHandler;
    private final SettingMemberLookup settingMemberLookup;
    private final ObjectMapper objectMapper;

    @Override
    public void process(EventEntry eventEntry) {
        NotificationCategory category = NotificationCategory.getType(eventEntry.getType());
        UUID receiverId = getReceiverId(eventEntry.getPayload());
        Optional<MemberInfo> memberInfo = settingMemberLookup.lookupByMemberId(new MemberId(receiverId));
        String message = String.format(category.getNotificationContent(), memberInfo.get().nickname());

        // 1. 알림 생성
        CreateNotificationCommand createNotificationCommand = new CreateNotificationCommand(category, receiverId, message);
        createNotificationCommandHandler.createNotification(createNotificationCommand);

        // 2. 푸시알림 생성
        PushNotificationRequest pushNotificationRequest = new PushNotificationRequest(receiverId, category, category.getTitle(), category.getPushNotificationContent(), ZonedDateTime.now());
        sendNotificationHandler.send(pushNotificationRequest);
    }

    private UUID getReceiverId(String payload) {
        try {
            JsonNode jsonNode = objectMapper.readTree(payload);
            String receiverId = jsonNode.get("exchangeRequest").get("requestMemberId").get("value").asText();
            return UUID.fromString(receiverId);
        } catch (JsonProcessingException e) {
            // TODO - 예외 처리
            throw new DomainException("JSON ERROR!");
        }
    }
}


