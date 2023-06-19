package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.AirportRequest;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Airport;
import com.binar.pemesanantiketpesawat.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping("/get-airport")
    public ResponseEntity<MessageModel> getAllAirport() {
        MessageModel messageModel = new MessageModel();
        List<Airport> airportResponse = airportService.getAllAirport();
        if (airportResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to get airport");
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success to get airport");
            messageModel.setData(airportResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @PostMapping("/add-airport")
    public ResponseEntity<MessageModel> addNewAirport(@RequestBody AirportRequest airportRequest) {
        MessageModel messageModel = new MessageModel();
        Airport airportResponse = airportService.addNewAirport(airportRequest);
        if (airportResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add airport");
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Add new airport");
            messageModel.setData(airportResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
