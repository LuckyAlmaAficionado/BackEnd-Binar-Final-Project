package com.binar.pemesanantiketpesawat.request;

import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.repository.BookingRepository;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookingRequest implements BookingService {
    @Autowired
    private BookingRepository repository;

    @Override
    public Booking saveDataBooking(Booking the_order) {
        return repository.save(the_order);
    }

    @Override
    public List<Booking> getAllPesanan() {
        return repository.findAll();
    }
}
