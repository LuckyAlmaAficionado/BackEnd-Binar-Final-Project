package com.binar.pemesanantiketpesawat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "time")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;
    @Column(name = "departure_date_fk")
    private Integer departureDateFk;
    @Column(name = "departure_time", nullable = false)
    private java.sql.Time departureTime;
    @Column(name = "arrival_time", nullable = false)
    private java.sql.Time arrivalTime;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "airline_time_fk", referencedColumnName = "schedule_id", insertable = true, updatable = true)
    private List<Airline> airlineList;
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @JsonIgnore
    @Column(name = "modified_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    public Time(Integer scheduleId, Integer departureDateFk, java.sql.Time departureTime, java.sql.Time arrivalTime, List<Airline> airlineList) {
        this.scheduleId = scheduleId;
        this.departureDateFk = departureDateFk;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airlineList = airlineList;
    }
}