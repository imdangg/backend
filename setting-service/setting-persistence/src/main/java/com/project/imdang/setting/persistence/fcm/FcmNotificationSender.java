package com.project.imdang.setting.persistence.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.client.MemberData;
import com.project.imdang.member.domain.client.MemberDataResolver;
import com.project.imdang.member.domain.client.exception.MemberNotFoundException;
import com.project.imdang.setting.domain.exception.MessagingException;
import com.project.imdang.setting.domain.ports.output.sender.NotificationRequest;
import com.project.imdang.setting.domain.ports.output.sender.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class FcmNotificationSender implements NotificationSender {

    private final MemberDataResolver memberResolver;

    @Retryable(
            retryFor = MessagingException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    @Override
    public void send(NotificationRequest notificationRequest) throws MessagingException {

        final MemberId receiverId = new MemberId(notificationRequest.getReceiverId());
        MemberData memberData = memberResolver.resolve(receiverId)
                .orElseThrow(() -> new MemberNotFoundException(receiverId));

        final String deviceToken = memberData.getDeviceToken();
        final String title = notificationRequest.getTitle();
        final String body = notificationRequest.getBody();
        Message message = getMessage(deviceToken, title, body);

        try {
            FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            throw new MessagingException(e.getMessage());
        }
    }

    private Message getMessage(String deviceToken, String title, String body) {

//        final AndroidConfig androidConfig = AndroidConfig.builder()
//                .setPriority(AndroidConfig.Priority.HIGH)
//                .setNotification(AndroidNotification.builder()
//                        .setTitle(title)
//                        .setBody(body)
//                        .build())
//                .build();
//        final ApnsConfig apnsConfig = ApnsConfig.builder()
//                .setAps(Aps.builder()
//                        .setAlert(ApsAlert.builder()
//                                .setTitle(title)
//                                .setBody(body)
//                                .build())
//                        .build())
//                .build();

        final Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        return Message.builder()
                .setToken(deviceToken)
                .setNotification(notification)
                .putData("time", ZonedDateTime.now().toString())
//                .setAndroidConfig(androidConfig)
//                .setApnsConfig(apnsConfig)
                .build();
    }
}
