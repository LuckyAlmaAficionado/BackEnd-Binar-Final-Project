package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private List<Booking> bookings;

    public BookingServiceImpl() {
        this.bookings = new ArrayList<>();
    }

    @Override
    public Booking saveDataBooking(Booking the_order) {
        bookings.add(the_order);
        return the_order;
    }

    @Override
    public List<Booking> getAllPesanan() {
        return bookings;
    }
}
