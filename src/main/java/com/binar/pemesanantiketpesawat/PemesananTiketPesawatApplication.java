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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.io.IOException;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PemesananTiketPesawatApplication {

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(PemesananTiketPesawatApplication.class, args);
    }

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfiguration).getInputStream());

        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(googleCredentials).build();

        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "ui3tyb782trc2b78bt378");
        return FirebaseMessaging.getInstance(app);
    }

}
