package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.TimeRequest;
import com.binar.pemesanantiketpesawat.model.Time;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.repository.TimeRepository;
import com.binar.pemesanantiketpesawat.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeRequestImpl implements TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Time addNewTime(TimeRequest timeRequest) {

        boolean scheduleResponse = scheduleRepository.findById(timeRequest.getDepartureDateFk()).isPresent();

        if (!scheduleResponse) {
            return null;
        }

        return timeRepository.save(new Time(
                0,
                timeRequest.getDepartureDateFk(),
                timeRequest.getDepartureTime(),
                timeRequest.getArrivalTime(),
                timeRequest.getLongFlight(),
                null
        ));
    }

    @Override
    public List<Time> getAllTime() {
        return timeRepository.findAll();
    }
}
