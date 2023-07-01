package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.model.NotificationMessage;
import com.binar.pemesanantiketpesawat.service.FirebaseMessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage) {
        log.info("Received request to send notification with title: '{}' and body: '{}'", notificationMessage.getTitle(), notificationMessage.getBody());

        String result = firebaseMessagingService.sendNotificationByToken(notificationMessage);


    @GetMapping
    private List<NotificationMessage> getNotificationByUUID(@RequestParam UUID uuidRequest) {
        return firebaseMessagingService.getByUUID(uuidRequest);

        log.info("Notification sent successfully");

        return result;
    }
}
