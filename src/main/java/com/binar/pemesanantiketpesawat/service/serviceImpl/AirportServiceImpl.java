package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.AirportRequest;
import com.binar.pemesanantiketpesawat.model.Airport;
import com.binar.pemesanantiketpesawat.repository.AirportRepository;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Airport addNewAirport(AirportRequest airportRequest) {

        Boolean IATAResponse = airportRepository.existsByAirportIATA(airportRequest.getAirportIATA());

        if (!IATAResponse) {
            Airport airport = new Airport(
                    0,
                    airportRequest.getAirportLocation(),
                    airportRequest.getAirportProvince(),
                    airportRequest.getAirportIATA(),
                    airportRequest.getAirportName()
            );
            return airportRepository.save(airport);
        }
        return null;
    }

    public List<String> getDepartureAirport() {
        List<String> scheduleResponse = scheduleRepository.findAllDepartureCity();
        scheduleResponse.forEach(System.out::println);
        return null;
    }

    public List<String> getArrivalAirport(String departureCity) {
        List<String> scheduleResponse = scheduleRepository.findArrivalCityFromDepartureCity(departureCity);
        return scheduleResponse;
    }

    @Override
    public List<Airport> getAllAirport() {

        List<String> scheduleResponse = scheduleRepository.findAllDepartureCity();
        scheduleResponse.forEach(s -> {
            System.out.println("departure airport: " + s);
            System.out.println(getArrivalAirport(s));
        });
//        List<String> scheduleResponse = scheduleRepository.findDepartureCityFromArrivalCity("Melbourne");
//
//        for (String schedule : scheduleResponse)
//            System.out.println(schedule);

        return airportRepository.findAll();
    }
}
