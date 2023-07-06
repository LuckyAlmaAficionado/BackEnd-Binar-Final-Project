package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.model.NotificationMessage;
import com.binar.pemesanantiketpesawat.service.FirebaseMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage) {
        log.info("Received request to send notification with title: '{}' and body: '{}'", notificationMessage.getTitle(), notificationMessage.getBody());
        return  firebaseMessagingService.sendNotificationByToken(notificationMessage);
    }

    @Transactional
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteNotificationByUUID(@RequestParam UUID uuidRequest) {
        return firebaseMessagingService.deleteNotificationByUUID(uuidRequest);
    }

    @GetMapping
    private List<NotificationMessage> getNotificationByUUID(@RequestParam UUID uuidRequest) {
        log.info("Received request to get notifications by UUID: '{}'", uuidRequest);
        List<NotificationMessage> notifications = firebaseMessagingService.getByUUID(uuidRequest);
        log.info("Retrieved {} notification(s) for UUID: '{}'", notifications.size(), uuidRequest);
        return notifications;
    }
}