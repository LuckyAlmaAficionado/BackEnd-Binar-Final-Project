package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.model.NotificationMessage;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    List<NotificationMessage> getByUUID(UUID uuidRequest);
    String sendNotificationByToken(NotificationMessage notificationMessage);
    String deleteNotificationByUUID(UUID uuidRequest);
    void deleteExpiredNotifications();
}
