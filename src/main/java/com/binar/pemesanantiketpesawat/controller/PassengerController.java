package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.model.Passenger;
import com.binar.pemesanantiketpesawat.request.PassengerRequest;
import com.binar.pemesanantiketpesawat.service.PassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    private static final Logger log = LoggerFactory.getLogger(PassengerController.class);

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<Passenger> getAllPenumpang() {
        log.info("Received request to get all passengers");

        List<Passenger> passengers = passengerService.getAllPenumpang();

        log.info("Retrieved {} passengers", passengers.size());

        return passengers;
    }

    @PostMapping("/Save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Passenger saveDataPenumpang(@RequestBody PassengerRequest the_passenger){
        log.info("Received request to save passenger");

        Passenger savedPassenger = passengerService.saveDataPenumpang(the_passenger);

        log.info("Passenger saved successfully");

        return savedPassenger;
    }

    @GetMapping("/findByIdPenumpang/{passenger_id}")
    Passenger findByIdPenumpang(@RequestParam Integer passenger_id) {
        log.info("Received request to find passenger by ID: {}", passenger_id);

        Passenger passenger = passengerService.findByIdPenumpang(passenger_id);

        if (passenger == null) {
            log.warn("Passenger with ID {} not found", passenger_id);
        } else {
            log.info("Passenger found: {}", passenger);
        }

        return passenger;
    }

    @PutMapping("/Update")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    Passenger updateEntityPenumpang(@RequestBody Passenger thePassenger) {
        log.info("Received request to update passenger");

        Passenger updatedPassenger = passengerService.updateEntityPenumpang(thePassenger);

        if (updatedPassenger == null) {
            log.warn("Failed to update passenger");
        } else {
            log.info("Passenger updated successfully");
        }

        return updatedPassenger;
    }

    @DeleteMapping("/delete/{passenger_id}")
    String deletePenumpang(@RequestParam Integer passenger_id) {
        log.info("Received request to delete passenger with ID: {}", passenger_id);

        String result = passengerService.deletePenumpang(passenger_id);

        if (result == null) {
            log.warn("Failed to delete passenger with ID: {}", passenger_id);
        } else {
            log.info("Passenger deleted successfully");
        }

        return result;
    }
}
