package com.project.imdang.setting.service.domain.handler;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.dto.PushNotificationRequest;
import com.project.imdang.setting.service.domain.exception.NotificationDomainException;
import com.project.imdang.setting.service.domain.exception.SettingApplicationServiceException;
import com.project.imdang.setting.service.domain.ports.output.lookup.SettingMemberLookup;
import com.project.imdang.setting.service.domain.valueobject.MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import static com.project.imdang.domain.exception.ErrorCode.MEMBER_NOT_EXIST;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendNotificationHandler {

    private final FirebaseMessaging firebaseMessaging;
    private final SettingMemberLookup settingMemberLookup;

    @Retryable(retryFor = FirebaseMessagingException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void send(PushNotificationRequest pushNotificationRequest) {

        MemberId memberId = new MemberId(pushNotificationRequest.getMemberId());
        String title = pushNotificationRequest.getTitle();
        String body = pushNotificationRequest.getBody();

        String token = getFcmToken(memberId);
        Notification notification = getNotification(title, body);
        // TODO - CHECK : Android Configuration
        AndroidConfig androidConfig = getAndroidConfig(title, body);
        // TODO - CHECK :  APNs Configuration
        Message message = Message.builder()
                .setToken(token)
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

    private Notification getNotification(String title, String body) {
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
    }

    private AndroidConfig getAndroidConfig(String title, String body) {
        return AndroidConfig.builder()
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .build();
    }

    private String getFcmToken(MemberId memberId) {
        MemberInfo memberInfo = settingMemberLookup.lookupByMemberId(memberId)
                .orElseThrow(() -> new SettingApplicationServiceException(MEMBER_NOT_EXIST));
        return memberInfo.deviceToken();
    }
}
