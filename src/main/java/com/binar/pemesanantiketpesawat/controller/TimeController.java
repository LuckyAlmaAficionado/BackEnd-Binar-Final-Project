package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.dto.TimeRequest;
import com.binar.pemesanantiketpesawat.model.Time;
import com.binar.pemesanantiketpesawat.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/time")
public class TimeController {

    private static final Logger logger = LoggerFactory.getLogger(TimeController.class);

    @Autowired
    private TimeService timeService;

    @PostMapping("/add")
    public ResponseEntity<MessageModel> addNewTime(@RequestBody TimeRequest timeRequest) {
        MessageModel messageModel = new MessageModel();
        Time timeResponse = timeService.addNewTime(timeRequest);
        if (timeResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add new time");
            logger.error("Failed to add new time");
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Successfully added new time");
            messageModel.setData(timeResponse);
            logger.info("Successfully added new time");
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<MessageModel> getAllTime() {
        MessageModel messageModel = new MessageModel();
        List<Time> timeResponse = timeService.getAllTime();
        if (timeResponse.isEmpty()) {
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("No time available");
            logger.info("No time available");
            return ResponseEntity.ok().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Successfully retrieved all time");
            messageModel.setData(timeResponse);
            logger.info("Successfully retrieved all time");
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
