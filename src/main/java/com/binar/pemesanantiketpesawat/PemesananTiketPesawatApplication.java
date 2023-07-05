package com.binar.pemesanantiketpesawat;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@SpringBootApplication
public class PemesananTiketPesawatApplication {

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(PemesananTiketPesawatApplication.class, args);
    }

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfiguration).getInputStream()))
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions);
        return FirebaseMessaging.getInstance(app);
    }
}
