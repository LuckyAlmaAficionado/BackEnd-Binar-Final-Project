package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.BookingRequest;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingControllerTest {
    @Mock
    private BookingService bookingService;

    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingController = new BookingController(bookingService);
    }

    @Test
    void testAddBooking() {
        // Create a sample BookingRequest
        UUID uuidUser = UUID.randomUUID();
        String randomToken = "http://mahasiswa.dinus.ac.id/images/foto/A/A11/2020/A11.2020.12870.jpg";
        String airlineCode = "GA-544";
        String flightClass = "Economy";
        Integer adult = 1;
        Integer child = 0;
        Integer baby = 0;
        BookingRequest bookingRequest = new BookingRequest(uuidUser, randomToken, airlineCode, flightClass, adult, child, baby, null, null);

        // Mock the behavior of bookingService.saveDataBooking() method
        Booking savedBooking = new Booking();
        when(bookingService.saveDataBooking(bookingRequest)).thenReturn(savedBooking);

        // Call the controller method
        Booking result = bookingController.addBooking(bookingRequest);

        // Verify the interactions and assertions
        verify(bookingService, times(1)).saveDataBooking(bookingRequest);
        assertEquals(savedBooking, result);
    }

    @Test
    void testGetBookingByCodeRequest_Success() {
        // Set up test data
        String bookingCodeRequest = "GA-544";
        Booking bookingResponse = new Booking();

        // Mock the behavior of bookingService.searchBookingByCode() method
        when(bookingService.searchBookingByCode(bookingCodeRequest)).thenReturn(bookingResponse);

        // Call the controller method
        ResponseEntity<MessageModel> response = bookingController.getBookingByCodeRequest(bookingCodeRequest);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() != null && response.getBody().getMessage().equalsIgnoreCase("managed to get the data"));
        assertEquals(bookingResponse, response.getBody().getData());
    }


    @Test
    void testGetBookingByCodeRequest_NoDataFound() {
        // Set up test data
        String bookingCodeRequest = "GA-544";

        // Mock the behavior of bookingService.searchBookingByCode() method
        when(bookingService.searchBookingByCode(bookingCodeRequest)).thenReturn(null);

        // Call the controller method
        ResponseEntity<MessageModel> response = bookingController.getBookingByCodeRequest(bookingCodeRequest);

        // Verify the response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getMessage().equalsIgnoreCase("no data found"));
    }


    @Test
    void testGetDataPemesan() {
        // Set up test data
        List<Booking> bookingList = Collections.singletonList(new Booking());

        // Mock the behavior of bookingService.getAllPesanan() method
        when(bookingService.getAllPesanan()).thenReturn(bookingList);

        // Call the controller method
        List<Booking> result = bookingController.getDataPemesan();

        // Verify the interactions and assertions
        verify(bookingService, times(1)).getAllPesanan();
        assertEquals(bookingList, result);
    }
}
