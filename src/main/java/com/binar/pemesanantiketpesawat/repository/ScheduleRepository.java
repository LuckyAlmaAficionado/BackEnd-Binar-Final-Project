package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Date;
import java.util.List;

@EnableJpaRepositories
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByDepartureDateAndDepartureCityAndArrivalCity(Date date, String departure, String arrival);
    Schedule findByTimeId(Integer waktuId);
//    SELECT distinct arrival_city AS kota FROM `e-flight`.schedule UNION ALL SELECT distinct departure_city FROM `e-flight`.schedule;
    @Query(value = "SELECT DISTINCT arrival_city AS kota FROM schedule UNION ALL SELECT DISTINCT departure_city FROM schedule", nativeQuery = true)
    List<String> findAllCountry();

    @Query(value = "SELECT DISTINCT s.arrivalCity FROM Schedule s WHERE s.departureCity = ?1")
    List<String> findArrivalCityFromDepartureCity(String departureCity);
    @Query(value = "SELECT DISTINCT s.departureCity FROM Schedule s")
    List<String> findAllDepartureCity();

}
