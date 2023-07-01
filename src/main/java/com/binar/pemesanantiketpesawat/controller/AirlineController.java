package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.AirlineRequest;
import com.binar.pemesanantiketpesawat.dto.AirlineResponse;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.service.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/airline")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @PostMapping("/add-airline")
    public ResponseEntity<MessageModel> addNewAirline(@RequestBody AirlineRequest airlineRequest) {
        log.info("Received request to add new airline");

        MessageModel messageModel = new MessageModel();
        Airline airlineResponse = airlineService.addNewAirline(airlineRequest);
        if (airlineResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            messageModel.setMessage("Failed to add new airline");
            log.error("Failed to add new airline");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Successfully added new airline");
            messageModel.setData(airlineResponse);
            log.info("Successfully added new airline");
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/airlineCode")
    public ResponseEntity<MessageModel> searchByAirlineCode(@RequestParam String codeRequest) {
        log.info("Received request to get airline by code: {}", codeRequest);

        MessageModel messageModel = new MessageModel();
        Airline airlineResponse = airlineService.searchByAirlineCode(codeRequest);
        if (airlineResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to get airline by code " + codeRequest);
            log.error("Failed to get airline by code: {}", codeRequest);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Successfully got airline by code " + codeRequest);
            messageModel.setData(airlineResponse);
            log.info("Successfully got airline by code: {}", codeRequest);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/all-airline")
    public ResponseEntity<MessageModel> getAllAirline() {
        log.info("Received request to get all airlines");

        MessageModel messageModel = new MessageModel();
        List<AirlineResponse> airlineResponse = airlineService.getAllAirline().stream()
                .map(airline -> new AirlineResponse(
                        airline.getAirlineId(),
                        airline.getAirlineName(),
                        airline.getAirlineCode()
                ))
                .collect(Collectors.toList());

        if (airlineResponse.isEmpty()) {
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            messageModel.setMessage("Failed to get all airlines");
            log.error("Failed to get all airlines");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Successfully got all airlines");
            messageModel.setData(airlineResponse);
            log.info("Successfully got all airlines");
            return ResponseEntity.ok().body(messageModel);
        }
    }

}
