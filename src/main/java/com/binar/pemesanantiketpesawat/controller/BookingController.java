package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.BookingRequest;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Booking addBooking(@RequestBody BookingRequest bookingRequest) {
        log.info("Received request to add booking");

        Booking booking = bookingService.saveDataBooking(bookingRequest);

        log.info("Booking added successfully");

        return booking;
    }

    @GetMapping("/search-booking-code")
    ResponseEntity<MessageModel> getBookingByCodeRequest(@RequestParam("bookingCodeRequest") String bookingCodeRequest) {
        log.info("Received request to search booking by code");

        Booking bookingResponse = bookingService.searchBookingByCode(bookingCodeRequest);
        MessageModel<Booking> messageModel = new MessageModel<>();

        if (bookingResponse == null) {
            log.info("No booking found with the provided code");

            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("No data found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messageModel);
        } else {
            log.info("Booking found with the provided code");

            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Managed to get the data");
            messageModel.setData(bookingResponse);
            return ResponseEntity.ok(messageModel);
        }
    }

    @GetMapping("/getDataPemesan")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Booking> getDataPemesan() {
        log.info("Received request to get all bookings");

        List<Booking> bookings = bookingService.getAllPesanan();

        log.info("Successfully retrieved all bookings");

        return bookings;
    }

    @Modifying
    @Transactional
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBooking(@RequestParam UUID uuidRequest) {
        return bookingService.deleteBookingByUuidUser(uuidRequest);
    }
}
