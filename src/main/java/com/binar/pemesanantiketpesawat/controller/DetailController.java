package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import com.binar.pemesanantiketpesawat.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/detail-information")
public class DetailController {

    @Autowired
    private DetailService detailService;

    @GetMapping
    public DetailFlight filterDataFlight(
            @RequestParam("airlineCode") String airlineCode,
            @RequestParam("flightClass") String flightClass,
            @RequestParam("adultPassenger") Integer adultPassenger,
            @RequestParam("childrenPassenger") Integer childrenPassenger,
            @RequestParam("babyPassenger") Integer babyPassenger
    )  {
        return detailService.getDetailPenerbangan(airlineCode, flightClass, adultPassenger, childrenPassenger, babyPassenger);
    }

}
