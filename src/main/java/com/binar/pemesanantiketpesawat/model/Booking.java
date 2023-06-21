package com.binar.pemesanantiketpesawat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;
    @Column (name = "booking_code")
    private String bookingCode;
    @Column (name = "departure_date")
    private Date departureDate;
    @Column (name = "departure_airport")
    private String departureAirport;
    @Column (name = "arrival_time")
    private Time arrivalTime;
    @Column (name = "flight_code")
    private String flightCode;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Order order;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "booking_fk", referencedColumnName = "booking_id", updatable = true, insertable = true)
    private List<Passenger> passengers;
}