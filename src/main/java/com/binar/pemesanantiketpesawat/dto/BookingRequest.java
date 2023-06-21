package com.binar.pemesanantiketpesawat.dto;

import com.binar.pemesanantiketpesawat.model.Order;
import com.binar.pemesanantiketpesawat.model.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class BookingRequest {
    private String bookingCode;
    private Date departureDate;
    private  String departureAirport;
    private String arrivalTime;
    private String flightCode;
    private Order order;
    private List<Passenger> passengers;
}
