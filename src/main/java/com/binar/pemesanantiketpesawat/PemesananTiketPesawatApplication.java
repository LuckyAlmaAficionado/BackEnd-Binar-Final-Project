package com.binar.pemesanantiketpesawat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan("com.binar.pemesanantiketpesawat.model")
public class PemesananTiketPesawatApplication {


    public static void main(String[] args) {
        SpringApplication.run(PemesananTiketPesawatApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:3000",
                                "http://localhost:3001",
                                "https://binar-backend-final-project-production.up.railway.app",
                                "*");
            }
        };
    }
}
