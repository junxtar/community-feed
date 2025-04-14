package kr.amc.amis.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FcmConfig {

    @Value("${fcm.certification}")
    private String fcmApplicationCredentials;

    @PostConstruct
    public void initialize() {
        ClassPathResource classPathResource = new ClassPathResource(fcmApplicationCredentials);

        try (InputStream inputStream = classPathResource.getInputStream()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("FirebaseApp initialized");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
