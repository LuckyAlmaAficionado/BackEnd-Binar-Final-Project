package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.DetailFlightList;
import com.binar.pemesanantiketpesawat.dto.ScheduleRequest;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Schedule;
import com.binar.pemesanantiketpesawat.model.Seat;
import com.binar.pemesanantiketpesawat.model.Time;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.service.ScheduleService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired private ScheduleRepository scheduleRepository;

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

  @Override
  public Schedule addSchedule(ScheduleRequest scheduleRequest) {
    return scheduleRepository.save(
        new Schedule(
            0,
            scheduleRequest.getContinentCategory(),
            scheduleRequest.getFavoriteFlight(),
            scheduleRequest.getDepartureDate(),
            scheduleRequest.getDepartureCity(),
            scheduleRequest.getArrivalCity()));
  }

  @Override
  public void deleteAllAirplaneTicketSchedule() {
    scheduleRepository.deleteAll();
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
        .map(
            schedule ->
                new Schedule(
                    schedule.getTimeId(),
                    schedule.getFavoriteFlight(),
                    schedule.getDepartureDate(),
                    schedule.getDepartureCity(),
                    schedule.getArrivalCity(),
                    schedule.getSchedulesList().stream()
                        .map(
                            time ->
                                new Time(
                                    time.getScheduleId(),
                                    time.getDepartureDateFk(),
                                    time.getDepartureTime(),
                                    time.getArrivalTime(),
                                    time.getAirlineList().stream()
                                        .map(
                                            airline ->
                                                new Airline(
                                                    airline.getAirlineId(),
                                                    airline.getAirlineTimeFk(),
                                                    airline.getAirlineName(),
                                                    airline.getAirlineCode(),
                                                    airline.getFlightClass().stream()
                                                        .filter(
                                                            seats ->
                                                                seats
                                                                    .getFlightClass()
                                                                    .startsWith(seat))
                                                        .collect(Collectors.toList())))
                                        .filter(airline -> !airline.getFlightClass().isEmpty())
                                        .collect(Collectors.toList())))
                        .collect(Collectors.toList())))
        .collect(Collectors.toList());
  }
}
