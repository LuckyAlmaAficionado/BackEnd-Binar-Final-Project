package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.repository.BookingRepository;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveDataBooking(Booking orderRequest) {
        return bookingRepository.save(orderRequest);
    }

    @Override
    public List<Booking> getAllPesanan() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking searchBookingByCode(String bookingCodeRequest) {
        return bookingRepository.findBookingByBookingCode(bookingCodeRequest);
    }
}
