package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.DetailFlightList;
import com.binar.pemesanantiketpesawat.dto.ScheduleRequest;
import com.binar.pemesanantiketpesawat.model.Schedule;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public interface ScheduleService {
    List<Schedule> getAllSchedules();
    List<Schedule> searchAirplaneTicketOnlyPlane(Date date, String departure, String arrival, String seat);
    List<Schedule> searchAirplaneTicketSchedule(Date date, String departure, String arrival, String seat);
    List<DetailFlightList> filterDataSchedule(Date date, String departure, String arrival, String seat);
    Schedule addSchedule(ScheduleRequest scheduleRequest);
    void deleteAllAirplaneTicketSchedule();
}
