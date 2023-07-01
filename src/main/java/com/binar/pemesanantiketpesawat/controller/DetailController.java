package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import com.binar.pemesanantiketpesawat.service.DetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/detail-information")
public class DetailController {
    private static final Logger log = LoggerFactory.getLogger(DetailController.class);

    @Autowired
    private DetailService detailService;

    @GetMapping
    public DetailFlight filterDataFlight(
            @RequestParam("airlineCode") String airlineCode,
            @RequestParam("flightClass") String flightClass,
            @RequestParam("adultPassenger") Integer adultPassenger,
            @RequestParam("childrenPassenger") Integer childrenPassenger,
            @RequestParam("babyPassenger") Integer babyPassenger
    ) {
        log.info("Received request to filter flight data");

        DetailFlight detailFlight = detailService.getDetailPenerbangan(airlineCode, flightClass, adultPassenger, childrenPassenger, babyPassenger);

        log.info("Filtered flight data retrieved successfully");

        return detailFlight;
    }
}
