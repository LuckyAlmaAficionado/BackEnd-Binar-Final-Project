package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.AirlineRequest;
import com.binar.pemesanantiketpesawat.dto.AirlineResponse;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.service.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@Slf4j
class AirlineControllerTest {

    @Mock
    private AirlineService airlineService;

    @InjectMocks
    private AirlineController airlineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
        // Menguji fungsi addNewAirline ketika berhasil menambahkan maskapai penerbangan baru
    void testAddNewAirline_Success() {
        // Arrange
        AirlineRequest request = new AirlineRequest();
        request.setAirlineName("Garuda Indonesia");
        request.setAirlineCode("GA");

        Airline airline = new Airline();
        airline.setAirlineId(1);
        airline.setAirlineName("Garuda Indonesia");
        airline.setAirlineCode("GA");

        when(airlineService.addNewAirline(any(AirlineRequest.class))).thenReturn(airline);

        // Act
        ResponseEntity<MessageModel> responseEntity = airlineController.addNewAirline(request);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        MessageModel<Airline> messageModel = responseEntity.getBody();
        assertNotNull(messageModel);
        assertEquals(HttpStatus.OK.value(), messageModel.getStatus());
        assertEquals("Successfully added new airline", messageModel.getMessage());
        assertEquals(airline, messageModel.getData());
    }

    @Test
        // Menguji fungsi addNewAirline ketika gagal menambahkan maskapai penerbangan baru
    void testAddNewAirline_Failure() {
        // Arrange
        AirlineRequest request = new AirlineRequest();
        request.setAirlineName("Garuda Indonesia");
        request.setAirlineCode("GA");

        when(airlineService.addNewAirline(any(AirlineRequest.class))).thenReturn(null);

        // Act
        ResponseEntity<MessageModel> responseEntity = airlineController.addNewAirline(request);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_GATEWAY, responseEntity.getStatusCode());
        MessageModel<Airline> messageModel = responseEntity.getBody();
        assertNotNull(messageModel);
        assertEquals(HttpStatus.BAD_GATEWAY.value(), messageModel.getStatus());
        assertEquals("Failed to add new airline", messageModel.getMessage());
        assertNull(messageModel.getData());
    }
}
