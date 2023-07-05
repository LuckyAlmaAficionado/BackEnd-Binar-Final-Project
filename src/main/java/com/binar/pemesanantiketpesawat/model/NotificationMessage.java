package com.binar.pemesanantiketpesawat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification")
public class NotificationMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;
    private UUID uuidUser;
    private String recipientToken;
    private String title;
    private String body;
    private String image;

    // Add expirationDate field
    private LocalDateTime expirationDate;

    // Getter and setter for expirationDate
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public NotificationMessage(UUID uuidUser, String recipientToken, String title, String body, String image) {
        this.uuidUser = uuidUser;
        this.recipientToken = recipientToken;
        this.title = title;
        this.body = body;
        this.image = image;
    }
}
