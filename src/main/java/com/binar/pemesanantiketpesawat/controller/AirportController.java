package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.AirportRequest;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Airport;
import com.binar.pemesanantiketpesawat.service.AirportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping("/get-airport")
    public ResponseEntity<MessageModel> getAllAirport() {
        log.info("Received request to get all airports");

        MessageModel messageModel = new MessageModel();
        List<String> airportResponse = airportService.getAllAirport();
        if (airportResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to get airports");
            log.error("Failed to get airports");
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Successfully got airports");
            messageModel.setData(airportResponse);
            log.info("Successfully got all airports");
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/getDepartureAirport")
    public ResponseEntity<MessageModel> getDepartureAirport() {
        log.info("Received request to get departure airports");

        MessageModel messageModel = new MessageModel();
        List<String> airportResponse = airportService.getDepartureAirport();
        if (airportResponse == null) {
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("Failed to get departure airports");
            log.error("Failed to get departure airports");
            return ResponseEntity.noContent().build();
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Successfully got departure airports");
            messageModel.setData(airportResponse);
            log.info("Successfully got departure airports");
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/getArrivalAirport")
    public ResponseEntity<MessageModel> getArrivalAirport(@RequestParam String departureRequest) {
        log.info("Received request to get arrival airports for departure airport: {}", departureRequest);

        MessageModel messageModel = new MessageModel();
        List<String> airportResponse = airportService.getArrivalAirportFromDeparture(departureRequest);
        if (airportResponse == null) {
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("Failed to get arrival airports");
            log.error("Failed to get arrival airports");
            return ResponseEntity.noContent().build();
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Successfully got arrival airports");
            messageModel.setData(airportResponse);
            log.info("Successfully got arrival airports");
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @PostMapping("/add-airport")
    public ResponseEntity<MessageModel> addNewAirport(@RequestBody AirportRequest airportRequest) {
        log.info("Received request to add new airport");

        MessageModel messageModel = new MessageModel();
        Airport airportResponse = airportService.addNewAirport(airportRequest);
        if (airportResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add new airport");
            log.error("Failed to add new airport");
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Successfully added new airport");
            messageModel.setData(airportResponse);
            log.info("Successfully added new airport");
            log.info("Airport IATA: {}", airportResponse.getAirportIATA());
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
