package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.AirportRequest;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Airport;
import com.binar.pemesanantiketpesawat.service.AirportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AirportControllerTest {

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
        // Pengujian untuk metode getAllAirport()
    void testGetAllAirport() {
        // Data dummy
        List<String> dummyAirports = Arrays.asList("CGK - Soekarno-Hatta International Airport", "DPS - Ngurah Rai International Airport");

        // Mock service
        when(airportService.getAllAirport()).thenReturn(dummyAirports);

        // Panggil metode controller
        ResponseEntity<MessageModel> response = airportController.getAllAirport();

        // Verifikasi hasil response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MessageModel<List<String>> messageModel = response.getBody();
        assertEquals(HttpStatus.OK.value(), messageModel.getStatus());
        assertEquals("Successfully got airports", messageModel.getMessage());
        assertEquals(dummyAirports, messageModel.getData());

        // Verifikasi pemanggilan service
        verify(airportService, times(1)).getAllAirport();
        verifyNoMoreInteractions(airportService);
    }

    @Test
        // Pengujian untuk metode getDepartureAirport()
    void testGetDepartureAirport() {
        // Data dummy
        List<String> dummyDepartureAirports = Arrays.asList("CGK - Soekarno-Hatta International Airport", "DPS - Ngurah Rai International Airport");

        // Mock service
        when(airportService.getDepartureAirport()).thenReturn(dummyDepartureAirports);

        // Panggil metode controller
        ResponseEntity<MessageModel> response = airportController.getDepartureAirport();

        // Verifikasi hasil response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MessageModel<List<String>> messageModel = response.getBody();
        assertEquals(HttpStatus.OK.value(), messageModel.getStatus());
        assertEquals("Successfully got departure airports", messageModel.getMessage());
        assertEquals(dummyDepartureAirports, messageModel.getData());

        // Verifikasi pemanggilan service
        verify(airportService, times(1)).getDepartureAirport();
        verifyNoMoreInteractions(airportService);
    }

    @Test
        // Pengujian untuk metode getArrivalAirport()
    void testGetArrivalAirport() {
        // Data dummy
        String dummyDepartureAirport = "CGK - Soekarno-Hatta International Airport";
        List<String> dummyArrivalAirports = Arrays.asList("DPS - Ngurah Rai International Airport", "SUB - Juanda International Airport");

        // Mock service
        when(airportService.getArrivalAirportFromDeparture(dummyDepartureAirport)).thenReturn(dummyArrivalAirports);

        // Panggil metode controller
        ResponseEntity<MessageModel> response = airportController.getArrivalAirport(dummyDepartureAirport);

        // Verifikasi hasil response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MessageModel<List<String>> messageModel = response.getBody();
        assertEquals(HttpStatus.OK.value(), messageModel.getStatus());
        assertEquals("Successfully got arrival airports", messageModel.getMessage());
        assertEquals(dummyArrivalAirports, messageModel.getData());

        // Verifikasi pemanggilan service
        verify(airportService, times(1)).getArrivalAirportFromDeparture(dummyDepartureAirport);
        verifyNoMoreInteractions(airportService);
    }

    @Test
        // Pengujian untuk metode addNewAirport()
    void testAddNewAirport() {
        // Data dummy
        AirportRequest dummyAirportRequest = new AirportRequest("Jakarta", "DKI Jakarta", "CGK", "Soekarno-Hatta International Airport");
        Airport dummyAirportResponse = new Airport("CGK", "Soekarno-Hatta International Airport");

        // Mock service
        when(airportService.addNewAirport(dummyAirportRequest)).thenReturn(dummyAirportResponse);

        // Panggil metode controller
        ResponseEntity<MessageModel> response = airportController.addNewAirport(dummyAirportRequest);

        // Verifikasi hasil response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MessageModel<Airport> messageModel = response.getBody();
        assertEquals(HttpStatus.OK.value(), messageModel.getStatus());
        assertEquals("Successfully added new airport", messageModel.getMessage());
        assertEquals(dummyAirportResponse, messageModel.getData());

        // Verifikasi pemanggilan service
        verify(airportService, times(1)).addNewAirport(dummyAirportRequest);
        verifyNoMoreInteractions(airportService);
    }
}
