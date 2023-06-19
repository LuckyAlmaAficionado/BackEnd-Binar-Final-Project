package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.ScheduleRequest;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Schedule;
import com.binar.pemesanantiketpesawat.model.Time;
import com.binar.pemesanantiketpesawat.repository.AirlineRepository;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.repository.SeatRepository;
import com.binar.pemesanantiketpesawat.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> searchAirplaneTicketSchedule(Date date, String departure, String arrival, String seat) {
        List<Schedule> scheduleResponse = scheduleRepository.findByDepartureDateAndDepartureCityAndArrivalCity(date, departure, arrival);
        return filterDataSearch(scheduleResponse, seat);
    }


    @Override
    public Schedule addSchedule(ScheduleRequest scheduleRequest) {
        return scheduleRepository.save(new Schedule(
                0,
                scheduleRequest.getContinentCategory(),
                scheduleRequest.getFavoriteFlight(),
                scheduleRequest.getDepartureDate(),
                scheduleRequest.getDepartureCity(),
                scheduleRequest.getArrivalCity()
        ));
    }

    @Override
    public void deleteAllAirplaneTicketSchedule() {
        scheduleRepository.deleteAll();
    }

    public List<Schedule> filterDataSearch(List<Schedule> scheduleResponse, String seat) {
        return scheduleResponse.stream()
                .map(schedule -> new Schedule(
                        schedule.getTimeId(),
                        schedule.getFavoriteFlight(),
                        schedule.getDepartureDate(),
                        schedule.getDepartureCity(),
                        schedule.getArrivalCity(),
                        schedule.getSchedulesList().stream()
                                .map(time -> new Time(
                                        time.getScheduleId(),
                                        time.getDepartureDateFk(),
                                        time.getDepartureTime(),
                                        time.getArrivalTime(),
                                        time.getAirlineList().stream()
                                                .map(airline -> new Airline(
                                                        airline.getAirlineId(),
                                                        airline.getAirlineTimeFk(),
                                                        airline.getAirlineName(),
                                                        airline.getAirlineCode(),
                                                        airline.getFlightClass().stream()
                                                                .filter(seats -> seats.getFlightClass().startsWith(seat))
                                                                .collect(Collectors.toList())
                                                )).filter(airline -> !airline.getFlightClass().isEmpty())
                                                .collect(Collectors.toList())
                                )).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }
}

























