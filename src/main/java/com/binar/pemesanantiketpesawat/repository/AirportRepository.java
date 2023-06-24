package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    Boolean existsByAirportIATA(String IATAResponse);

    Airport findByAirportLocation(String airportRequest);
}
