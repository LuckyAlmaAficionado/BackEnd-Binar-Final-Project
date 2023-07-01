package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/history")
public class HistoryController {
    private static final Logger log = LoggerFactory.getLogger(HistoryController.class);

    @Autowired
    private BookingService bookingService;

    @GetMapping
    private List<Booking> findByUUID(@RequestParam UUID uuidRequest) {
        log.info("Received request to find booking history for UUID: {}", uuidRequest);

        List<Booking> bookingList = bookingService.findByUuidUser(uuidRequest);

        if (bookingList.isEmpty()) {
            log.info("No booking history found for UUID: {}", uuidRequest);
        } else {
            log.info("Booking history retrieved successfully for UUID: {}", uuidRequest);
        }

        return bookingList;
    }
}
