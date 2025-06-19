package com.project.imdang.setting.persistence.fcm;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.client.MemberData;
import com.project.imdang.member.domain.client.MemberDataResolver;
import com.project.imdang.member.domain.client.exception.MemberNotFoundException;
import com.project.imdang.setting.domain.ports.output.sender.NotificationRequest;
import com.project.imdang.setting.domain.ports.output.sender.NotificationSender;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Component
public class FcmNotificationSender implements NotificationSender {

//    private final FirebaseMessaging firebaseMessaging;
    private final MemberDataResolver memberResolver;

    @Retryable(
            retryFor = FirebaseMessagingException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    @Override
    public void send(NotificationRequest notificationRequest) {

        final MemberId receiverId = new MemberId(notificationRequest.getReceiverId());
        final String title = notificationRequest.getTitle();
        final String body = notificationRequest.getBody();
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        MemberData memberData = memberResolver.resolve(receiverId)
                .orElseThrow(() -> new MemberNotFoundException(receiverId));

        // TODO - CHECK : Android Configuration
        AndroidConfig androidConfig = getAndroidConfig(title, body);
        // TODO - CHECK :  APNs Configuration

        Message message = Message.builder()
                .setToken(memberData.getDeviceToken())
                .setNotification(notification)
                .putData("time", ZonedDateTime.now().toString())
                .setAndroidConfig(androidConfig)
                .build();
//        try {
//            firebaseMessaging.send(message);
//        } catch (FirebaseMessagingException e) {
//            // TODO - 예외 처리
//            throw new RuntimeException(e);
//        }
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
}
