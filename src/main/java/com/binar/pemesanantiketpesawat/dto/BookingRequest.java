package com.binar.pemesanantiketpesawat.dto;

import com.binar.pemesanantiketpesawat.model.Customers;
import com.binar.pemesanantiketpesawat.model.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookingRequest {

    private UUID uuidUser;
    private String tokenFirebase;
    private String airlineCode;
    private String flightClass;
    private Integer adult;
    private Integer child;
    private Integer baby;
    private Customers customers;
    private List<Passenger> passengers;

    public BookingRequest(UUID uuidUser, String airlineCode, String flightClass, Integer adult, Integer child, Integer baby, Customers customers, List<Passenger> passengers) {
        this.uuidUser = uuidUser;
        this.airlineCode = airlineCode;
        this.flightClass = flightClass;
        this.adult = adult;
        this.child = child;
        this.baby = baby;
        this.customers = customers;
        this.passengers = passengers;
    }
}
