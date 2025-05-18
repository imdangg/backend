package com.project.imdang.setting.persistence.configuration;

import com.google.firebase.FirebaseApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FcmConfiguration {

    @Value("${firebase.config.path}")
    private String firebaseConfigPath;

    @Bean
    public FirebaseApp firebaseApp() {
        return null;
//        try {
//            InputStream serviceAccount = new ClassPathResource(firebaseConfigPath).getInputStream();
//
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//            log.info("Initialize Firebase Admin SDK complete");
//            return FirebaseApp.initializeApp(options);
//        } catch (FileNotFoundException e) {
//            throw new IllegalStateException();
//        } catch (IOException e) {
//            throw new FirebaseException("Failed initialize Firebase Admin SDK");
//        }
    }

//    @Bean
//    public FirebaseMessaging firebaseMessaging() {
//        try {
//            return FirebaseMessaging.getInstance(firebaseApp());
//        } catch (IllegalStateException e) {
//            throw new FirebaseException("Failed initialize FirebaseApp" + e.getMessage());
//        } catch (NullPointerException e) {
//            throw new FirebaseException("Failed load FirebaseApp" + e.getMessage());
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Failed read FirebaseConfigPath" + e.getMessage());
//        }
//    }
}
