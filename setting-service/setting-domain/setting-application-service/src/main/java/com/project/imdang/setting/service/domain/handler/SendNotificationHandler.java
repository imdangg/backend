package com.project.imdang.setting.service.domain.handler;

import com.google.firebase.messaging.*;
import com.project.imdang.setting.service.domain.dto.NotificationRequest;
import com.project.imdang.setting.service.domain.exception.NotificationDomainException;
import com.project.imdang.setting.service.domain.feign.SettingFeignClient;
import com.project.imdang.setting.service.domain.feign.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendNotificationHandler {

    private final FirebaseMessaging firebaseMessaging;
    private final SettingFeignClient settingFeignClient;

    @Retryable(retryFor = FirebaseMessagingException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void send(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .setTitle(notificationRequest.getTitle())
                .setBody(notificationRequest.getBody())
                .build();

        // TODO - CHECK : Android Configuration
        AndroidConfig androidConfig = AndroidConfig.builder()
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setTitle(notificationRequest.getTitle())
                        .setBody(notificationRequest.getBody())
                        .build())
                .build();

        //TODO - CHECK :  APNs Configuration

        Message message = Message.builder()
                .setToken(getFcmToken(notificationRequest.getMemberId()))
                .setNotification(notification)
                .setAndroidConfig(androidConfig)
                .build();

        try {
            String response = firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            log.error("Error Code : {}", e.getErrorCode().name());
            throw new NotificationDomainException("메세지 전송에 실패했습니다." + e.getMessage());
        }
    }

    private String getFcmToken(UUID memberId) {
        MemberInfoResponse memberInfoResponse = settingFeignClient.getMemberInfo(memberId);
        return memberInfoResponse.getDeviceToken();
    }
}
