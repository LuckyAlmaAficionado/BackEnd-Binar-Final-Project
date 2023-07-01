package com.binar.pemesanantiketpesawat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import javax.persistence.*;
import java.util.Map;
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


}
