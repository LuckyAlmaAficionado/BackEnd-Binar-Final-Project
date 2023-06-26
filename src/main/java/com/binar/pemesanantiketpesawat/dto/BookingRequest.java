package com.binar.pemesanantiketpesawat.dto;

import com.binar.pemesanantiketpesawat.model.Customers;
import com.binar.pemesanantiketpesawat.model.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookingRequest {

    private String airlineCode;
    private String flightClass;
    private Integer adult;
    private Integer child;
    private Integer baby;
    private Customers customers;
    private List<Passenger> passengers;

}
