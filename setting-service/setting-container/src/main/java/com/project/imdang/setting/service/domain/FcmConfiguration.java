package com.project.imdang.setting.service.domain;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.project.imdang.setting.service.domain.exception.FirebaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class FcmConfiguration {

    @Value("${firebase.config.path}")
    private String firebaseConfigPath;

    @Bean
    public FirebaseApp firebaseApp() {
        try {
            InputStream serviceAccount = new ClassPathResource(firebaseConfigPath).getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            return FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException();
        } catch (IOException e) {
            throw new FirebaseException("Failed initialize Firebase Admin SDK");
        }
    }

    @Bean
    public FirebaseMessaging firebaseMessaging() {
        try {
            return FirebaseMessaging.getInstance(firebaseApp());
        } catch (IllegalStateException e) {
            throw new FirebaseException("Failed initialize FirebaseApp" + e.getMessage());
        } catch (NullPointerException e) {
            throw new FirebaseException("Failed load FirebaseApp" + e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed read FirebaseConfigPath" + e.getMessage());
        }
    }
}
