package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.model.Booking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookingService {
    Booking saveDataBooking(Booking the_order);
    List<Booking> getAllPesanan();
}
