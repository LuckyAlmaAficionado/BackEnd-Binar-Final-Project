package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.model.NotificationMessage;
import com.binar.pemesanantiketpesawat.repository.NotificationMessageRepository;
import com.binar.pemesanantiketpesawat.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final FirebaseMessaging firebaseMessaging;
    private final NotificationMessageRepository repository;

    @Autowired
    public NotificationServiceImpl(FirebaseMessaging firebaseMessaging, NotificationMessageRepository repository) {
        this.firebaseMessaging = firebaseMessaging;
        this.repository = repository;
    }

    @Override
    public List<NotificationMessage> getByUUID(UUID uuidRequest) {
        return repository.findByUuidUser(uuidRequest);
    }

    @Override
    public String sendNotificationByToken(NotificationMessage notificationMessage) {
        repository.save(notificationMessage);

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
            String response = firebaseMessaging.send(message);
            return "Notification sent successfully with response: " + response;
        } catch (FirebaseMessagingException e) {
            return "Failed to send notification: " + e.getMessage();
        }
    }

    @Override
    @Transactional
    public String deleteNotificationByUUID(UUID uuidRequest) {
        repository.deleteByUuidUser(uuidRequest);
        return "Notification deleted successfully";
    }

    @Override
    @Scheduled(cron = "0 0 * * * *") // Run every hour
    @Transactional
    public void deleteExpiredNotifications() {
        List<NotificationMessage> notifications = repository.findAll();
        LocalDateTime now = LocalDateTime.now();

        notifications.forEach(notification -> {
            LocalDateTime expirationDate = notification.getExpirationDate();

            if (expirationDate != null && expirationDate.isBefore(now)) {
                repository.delete(notification);
            }
        });
    }
}
