package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.model.NotificationMessage;
import com.binar.pemesanantiketpesawat.repository.NotificationMessageRepository;
import com.binar.pemesanantiketpesawat.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Slf4j

@Service
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Autowired
    private NotificationMessageRepository repository;

    @Override
    public List<NotificationMessage> getByUUID(UUID uuidRequest) {
        log.info("Received request to get notifications by UUID: '{}'", uuidRequest);
        List<NotificationMessage> notifications = repository.findByUuidUser(uuidRequest);
        log.info("Retrieved {} notification(s) for UUID: '{}'", notifications.size(), uuidRequest);
        return notifications;
    }

    @Override
    public String sendNotificationByToken(NotificationMessage notificationMessage) {
        System.out.println("MASUK SINI");

        repository.save(new NotificationMessage(
                0,
                notificationMessage.getUuidUser(),
                notificationMessage.getRecipientToken(),
                notificationMessage.getTitle(),
                notificationMessage.getBody(),
                notificationMessage.getImage()
        ));

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
    @Override
    public String sendNotificationAutoByToken(NotificationMessage notificationMessage) {
        Notification notification = Notification.builder()
                .setTitle("Promosi")
                .setBody("Diskon Spesial Untuk Pelanggan Setia")
                .setImage("http://mahasiswa.dinus.ac.id/images/foto/A/A11/2020/A11.2020.12870.jpg")
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

    @Transactional
    @Modifying
    @Override
    public String deleteNotificationByUUID(UUID uuidRequest) {
        repository.deleteByUuidUser(uuidRequest);
        return "Success delete notification";
    }
}