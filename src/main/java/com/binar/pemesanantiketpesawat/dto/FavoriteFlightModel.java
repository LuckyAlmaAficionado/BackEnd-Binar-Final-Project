package com.binar.pemesanantiketpesawat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavoriteFlightModel {

    private String departureCity;
    private String arrivalCity;
    private String imageUrl;

}
