package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.model.NotificationMessage;
import com.binar.pemesanantiketpesawat.repository.NotificationMessageRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FirebaseMessagingService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Autowired
    private NotificationMessageRepository notificationMessage;

    public List<NotificationMessage> getByUUID(UUID uuidRequest) {
        return notificationMessage.findByUuidUser(uuidRequest);
    };

    public String sendNotificationByToken(NotificationMessage notificationMessage) {

        Notification notification = Notification.builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .setImage(notificationMessage.getImage())
                .build();

        Message message = Message.builder()
                .setToken(notificationMessage.getRecipientToken())
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
            return "Success Sending Notification";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Error Sending Notification";

        }
    }

    @Scheduled(cron = "0/10 55-58 8 * * *")
    public String sendNotificationAutoByToken(NotificationMessage notificationMessage) {

        Notification notification = Notification.builder()
                .setTitle("Promosi")
                .setBody("Diskon Spesial Untuk Pelanggan Setia")
                .setImage("https://st3.depositphotos.com/1064024/14272/i/450/depositphotos_142722813-stock-photo-heart-love-tree.jpg")
                .build();

        Message message = Message.builder()
                .setToken(notificationMessage.getRecipientToken())
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
            return "Success Sending Notification";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Error Sending Notification";

        }
    }
}
