package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.model.NotificationMessage;
import com.binar.pemesanantiketpesawat.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService firebaseMessagingService;

    @Autowired
    public NotificationController(NotificationService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> sendNotificationByToken(@RequestBody NotificationMessage notificationMessage) {
        log.info("Received request to send notification with title: '{}' and body: '{}'", notificationMessage.getTitle(), notificationMessage.getBody());
        String result = firebaseMessagingService.sendNotificationByToken(notificationMessage);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteNotificationByUUID(@RequestParam UUID uuidRequest) {
        String result = firebaseMessagingService.deleteNotificationByUUID(uuidRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    private ResponseEntity<List<NotificationMessage>> getNotificationByUUID(@RequestParam UUID uuidRequest) {
        log.info("Received request to get notification by UUID");
        List<NotificationMessage> notifications = firebaseMessagingService.getByUUID(uuidRequest);
        log.info("Notification sent successfully");
        return ResponseEntity.ok(notifications);
    }
}
