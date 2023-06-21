package com.binar.pemesanantiketpesawat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailFlightList {
    private String departureCity;
    private Time departureTime;
    private Date departureDate;
    private String arrivalCity;
    private Time arrivalTime;
    private String longFlight;
    private Date arrivalDate;
    private String airlineName;
    private String airlineCode;
    private String airlineClass;
    private Integer checkedBaggage;
    private Integer cabinBaggage;
    private String airlinePrice;
}
