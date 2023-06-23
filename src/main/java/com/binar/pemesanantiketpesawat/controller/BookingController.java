package com.binar.pemesanantiketpesawat.controller;


import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    private Booking addBooking(@RequestBody Booking bookingRequest) {
        return bookingService.saveDataBooking(bookingRequest);
    }

    @GetMapping("/getDataPemesan")
    public List<Booking> getDataPemesan() {
        return bookingService.getAllPesanan();
    }


}