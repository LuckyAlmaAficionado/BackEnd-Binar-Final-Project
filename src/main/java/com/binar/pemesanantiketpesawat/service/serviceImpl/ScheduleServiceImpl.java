package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import com.binar.pemesanantiketpesawat.dto.DetailFlightList;
import com.binar.pemesanantiketpesawat.dto.FavoriteFlightModel;
import com.binar.pemesanantiketpesawat.dto.ScheduleRequest;
import com.binar.pemesanantiketpesawat.model.*;
import com.binar.pemesanantiketpesawat.repository.AirportRepository;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> searchAirplaneTicketSchedule(
            Date date, String departure, String arrival, String seat) {
        List<Schedule> scheduleResponse =
                scheduleRepository.findByDepartureDateAndDepartureCityAndArrivalCity(
                        date, departure, arrival);
        return filterDataSearch(scheduleResponse, seat);
    }

    public String findByAirportLocation(String airportLocation) {
        Airport airportResponse = airportRepository.findByAirportLocation(airportLocation);
        return airportResponse.getAirportName();
    }

    @Override
    public Schedule addSchedule(ScheduleRequest scheduleRequest) {
        return scheduleRepository.save(
                new Schedule(
                        scheduleRequest.getContinentCategory(),
                        scheduleRequest.getFavoriteFlight(),
                        scheduleRequest.getDepartureDate(),
                        scheduleRequest.getDepartureCity(),
                        findByAirportLocation(scheduleRequest.getDepartureCity()),
                        scheduleRequest.getArrivalCity(),
                        findByAirportLocation(scheduleRequest.getArrivalCity())
                )
        );
//
//        airportRepository.findByAirportLocation(scheduleRequest.getArrivalCity());
//
//        return scheduleRepository.save(
//                new Schedule(
//                        scheduleRequest.getContinentCategory(),
//                        scheduleRequest.getFavoriteFlight(),
//                        Date.valueOf(scheduleRequest.getDepartureDate().toString()),
//                        scheduleRequest.getDepartureCity(),
//                        findByAirportLocation(scheduleRequest.getDepartureCity()),
//                        scheduleRequest.getArrivalCity(),
//                        findByAirportLocation(scheduleRequest.getArrivalCity())
//                ));
    }

    @Override
    public void deleteAllAirplaneTicketSchedule() {
        scheduleRepository.deleteAll();
    }

    @Override
    public Schedule updateSchedule(Schedule scheduleRequest) {
        Schedule scheduleResponse = scheduleRepository.findByTimeId(scheduleRequest.getTimeId());
        if (scheduleResponse == null) return null;
        else {
            scheduleResponse.setContinentCategory(scheduleRequest.getContinentCategory());
            scheduleResponse.setFavoriteFlight(scheduleRequest.getFavoriteFlight());
            scheduleResponse.setDepartureDate(scheduleRequest.getDepartureDate());
            scheduleResponse.setDepartureCity(scheduleRequest.getDepartureCity());
            scheduleResponse.setDepartureAirport(scheduleRequest.getDepartureAirport());
            scheduleResponse.setArrivalCity(scheduleRequest.getArrivalCity());
            scheduleResponse.setArrivalAirport(scheduleRequest.getArrivalAirport());
            return scheduleResponse;
        }
    }

    @Override
    public List<FavoriteFlightModel> findByFavoriteDestination() {
        List<Schedule> favoriteFlight = scheduleRepository.findByFavoriteFlight(true);

        favoriteFlight.forEach(s -> s.getDepartureCity());

        List<DetailFlightList> detailFlightList = null;

        for (Schedule flightSchedule : favoriteFlight) {
            for (Time flightTime : flightSchedule.getSchedulesList()) {
                for (Airline airline : flightTime.getAirlineList()) {
                    for (Seat seat : airline.getFlightClass()) {
                        detailFlightList = filterDataSchedule(
                                flightSchedule.getDepartureDate(),
                                flightSchedule.getDepartureCity(),
                                flightSchedule.getArrivalCity(),
                                seat.getFlightClass()
                        );
                    }
                }
            }
        }

        List<FavoriteFlightModel> favoriteFlightModels = detailFlightList.stream()
                .map(detail -> {
                    return new FavoriteFlightModel(
                            detail.getDepartureCity(),
                            detail.getArrivalCity(),
                            "https://images.pexels.com/photos/3348363/pexels-photo-3348363.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
                    );
                }).collect(Collectors.toList());

        return favoriteFlightModels;
    }

    @Override
    public List<Schedule> searchAirplaneTicketOnlyPlane(
            Date date, String departure, String arrival, String seat) {
        List<Schedule> scheduleResponse =
                scheduleRepository.findByDepartureDateAndDepartureCityAndArrivalCity(
                        date, departure, arrival);
        return filterDataSearch(scheduleResponse, seat);
    }

    @Override
    public List<DetailFlightList> filterDataPriceDesc(
            Date date, String depAirport, String arrAirport, String flightClass) {
        List<DetailFlightList> flightResponse =
                filterDataSchedule(date, depAirport, arrAirport, flightClass);

        List<DetailFlightList> collect =
                flightResponse.stream()
                        .sorted((o1, o2) -> o2.getAirlinePrice().compareTo(o1.getAirlinePrice()))
                        .collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<DetailFlightList> filterDataPriceAsc(
            Date date, String depAirport, String arrAirport, String flightClass) {
        List<DetailFlightList> flightResponse =
                filterDataSchedule(date, depAirport, arrAirport, flightClass);

        List<DetailFlightList> resultAsc =
                flightResponse.stream()
                        .sorted((o1, o2) -> o1.getAirlinePrice().compareTo(o2.getAirlinePrice()))
                        .collect(Collectors.toList());

        return resultAsc;
    }

    public List<DetailFlightList> filterDataSchedule(
            Date date, String departure, String arrival, String seat) {

        List<Schedule> scheduleResponse =
                filterDataSearch(searchAirplaneTicketSchedule(date, departure, arrival, seat), seat);

        List<DetailFlightList> tempDetailFlightList = new ArrayList<>();

        for (Schedule schedule : scheduleResponse)
            for (Time time : schedule.getSchedulesList())
                for (Airline airline : time.getAirlineList()) {
                    for (Seat flightClass : airline.getFlightClass()) {

                        tempDetailFlightList.add(
                                new DetailFlightList(
                                        schedule.getDepartureCity(),
                                        time.getDepartureTime(),
                                        schedule.getDepartureDate(),
                                        schedule.getArrivalCity(),
                                        time.getArrivalTime(),
                                        Duration.between(time.getDepartureTime().toLocalTime(), time.getArrivalTime().toLocalTime()),
                                        schedule.getDepartureDate(),
                                        airline.getAirlineName(),
                                        airline.getAirlineCode(),
                                        seat,
                                        flightClass.getAirlineBaggage(),
                                        flightClass.getAirlineCabinBaggage(),
                                        flightClass.getAirlinePrice()));
                    }
                }

        return tempDetailFlightList;
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
                                        time.getLongFlight(),
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
