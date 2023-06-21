package com.binar.pemesanantiketpesawat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.binar.pemesanantiketpesawat.model")
public class PemesananTiketPesawatApplication {


    public static void main(String[] args) {
        SpringApplication.run(PemesananTiketPesawatApplication.class, args);
    }

}
