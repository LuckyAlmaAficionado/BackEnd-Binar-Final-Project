package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.model.NotificationMessage;
import java.util.List;
import java.util.UUID;

public interface FirebaseMessagingService {
    List<NotificationMessage> getByUUID(UUID uuidRequest);
    String sendNotificationByToken(NotificationMessage notificationMessage);
    String sendNotificationAutoByToken(NotificationMessage notificationMessage);
    String deleteNotificationByUUID(UUID uuidRequest);
}
