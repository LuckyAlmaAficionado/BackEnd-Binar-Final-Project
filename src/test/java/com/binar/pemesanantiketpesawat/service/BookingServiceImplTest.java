package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.BookingRequest;
import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.model.Customers;
import com.binar.pemesanantiketpesawat.model.Passenger;
import com.binar.pemesanantiketpesawat.repository.AirlineRepository;
import com.binar.pemesanantiketpesawat.repository.BookingRepository;
import com.binar.pemesanantiketpesawat.service.serviceImpl.BookingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private DetailService detailService;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPesanan() {
        // Mocking the necessary dependencies
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        bookings.add(new Booking());

        when(bookingRepository.findAll()).thenReturn(bookings);

        // Call the method to be tested
        List<Booking> result = bookingService.getAllPesanan();

        // Verify the interactions and assertions
        verify(bookingRepository, times(1)).findAll();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testFindByUuidUser() {
        // Mocking the necessary dependencies
        UUID uuid = UUID.randomUUID();
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        bookings.add(new Booking());

        when(bookingRepository.findByUuidUser(uuid)).thenReturn(bookings);

        // Call the method to be tested
        List<Booking> result = bookingService.findByUuidUser(uuid);

        // Verify the interactions and assertions
        verify(bookingRepository, times(1)).findByUuidUser(uuid);

        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testSearchBookingByCode() {
        // Mocking the necessary dependencies
        String bookingCode = "ABC123";
        Booking booking = new Booking();

        when(bookingRepository.findBookingByBookingCode(bookingCode)).thenReturn(booking);

        // Call the method to be tested
        Booking result = bookingService.searchBookingByCode(bookingCode);

        // Verify the interactions and assertions
        verify(bookingRepository, times(1)).findBookingByBookingCode(bookingCode);

        Assertions.assertNotNull(result);
    }
}