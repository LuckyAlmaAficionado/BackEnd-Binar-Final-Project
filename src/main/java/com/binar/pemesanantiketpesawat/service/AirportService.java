package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.AirportRequest;
import com.binar.pemesanantiketpesawat.model.Airport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AirportService {

    Airport addNewAirport(AirportRequest airportRequest);

    List<Airport> getAllAirport();
}
