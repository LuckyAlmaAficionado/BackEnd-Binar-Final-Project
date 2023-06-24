package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.BookingRequest;
import com.binar.pemesanantiketpesawat.model.Booking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookingService {
    Booking saveDataBooking(BookingRequest bookingRequest);
    Booking searchBookingByCode(String bookingCodeRequest);
    List<Booking> getAllPesanan();
}
