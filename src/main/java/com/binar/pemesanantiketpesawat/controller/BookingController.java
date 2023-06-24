package com.binar.pemesanantiketpesawat.controller;


import com.binar.pemesanantiketpesawat.dto.BookingRequest;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    private Booking addBooking(@RequestBody BookingRequest bookingRequest) {
        return bookingService.saveDataBooking(bookingRequest);
    }

    @GetMapping("/search-booking-code")
    private ResponseEntity<MessageModel> getBookingByCodeRequest(@RequestParam("bookingCodeRequest") String bookingCodeRequest) {
        System.out.println("masuk sini ga mas");
        Booking bookingResponse = bookingService.searchBookingByCode(bookingCodeRequest);
        MessageModel messageModel = new MessageModel();
        if (bookingResponse == null) {
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("no data found");
            return ResponseEntity.badRequest().body(messageModel);
        }else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("managed to get the data");
            messageModel.setData(bookingResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/getDataPemesan")
    public List<Booking> getDataPemesan() {
        return bookingService.getAllPesanan();
    }


}