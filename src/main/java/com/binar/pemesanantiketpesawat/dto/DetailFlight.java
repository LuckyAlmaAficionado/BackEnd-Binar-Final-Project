package com.binar.pemesanantiketpesawat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailFlight {

    private String departureAirport;
    private Time departureTime;
    private Date departureDate;
    private String arrivalAirport;
    private Time arrivalTime;
    private Date arrivalDate;
    private String airlineName;
    private String airlineCode;
    private Integer checkedBaggage;
    private Integer cabinBaggage;
    private Integer adultPrice;
    private Integer childPrice;
    private Integer infantPrice;
    private Integer totalPrice;

    public DetailFlight(String departureAirport, Time departureTime, Date departureDate, String arrivalAirport, Time arrivalTime, Date arrivalDate, String airlineName, String airlineCode, Integer checkedBaggage, Integer cabinBaggage, Integer adultPrice, Integer childPrice, Integer infantPrice) {
        this.departureAirport = departureAirport;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.arrivalAirport = arrivalAirport;
        this.arrivalTime = arrivalTime;
        this.arrivalDate = arrivalDate;
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.checkedBaggage = checkedBaggage;
        this.cabinBaggage = cabinBaggage;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.infantPrice = infantPrice;
    }
}
