package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Schedule;
import com.binar.pemesanantiketpesawat.model.Seat;
import com.binar.pemesanantiketpesawat.model.Time;
import com.binar.pemesanantiketpesawat.repository.AirlineRepository;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.repository.SeatRepository;
import com.binar.pemesanantiketpesawat.repository.TimeRepository;
import com.binar.pemesanantiketpesawat.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public DetailFlight getDetailPenerbangan(String codeRequest, String classResponse, Integer adultsPassenggers, Integer childrensPassenggers, Integer babyPassenggers) {

        final Integer TAX = 300000;

        Airline airlineResponse = airlineRepository.findByAirlineCode(codeRequest);

        List<Seat> seatResponse = seatRepository.findByAirlineCodeFkAndFlightClass(airlineResponse.getAirlineId(), classResponse);

        Time timeResponse = timeRepository.findByScheduleId(airlineResponse.getAirlineTimeFk());

        Schedule scheduleResponse = scheduleRepository.findByTimeId(timeResponse.getDepartureDateFk());

        String tempSeatResponse = seatResponse.get(0).getAirlinePrice();
        String[] split = tempSeatResponse.split("\\s+");
        String tempSplit = split[1].replace(".", "");
        Integer price = Integer.valueOf(tempSplit);

        int totalAdultPassengers = price * adultsPassenggers;
        int totalChildPassengers = price * childrensPassenggers;
        int totalBabyPassangers = 0;
        Integer totalHarga = totalAdultPassengers + totalChildPassengers + totalBabyPassangers + TAX;

        return new DetailFlight(
            scheduleResponse.getDepartureCity(),
                timeResponse.getDepartureTime(),
                scheduleResponse.getDepartureDate(),
                scheduleResponse.getArrivalCity(),
                timeResponse.getArrivalTime(),
                scheduleResponse.getDepartureDate(),
                airlineResponse.getAirlineName() + " - " + classResponse,
                codeRequest,
                seatResponse.get(0).getAirlineBaggage(),
                seatResponse.get(0).getAirlineCabinBaggage(),
                totalAdultPassengers,
                totalChildPassengers,
                totalBabyPassangers,
                totalHarga
        );

    }
}