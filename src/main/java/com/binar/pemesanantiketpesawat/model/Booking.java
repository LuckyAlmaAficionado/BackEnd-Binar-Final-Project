package com.binar.pemesanantiketpesawat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking_details")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    private String bookingCode;

    // departure
    private String departureAirport;
    private Date departureDate;
    private Time departureTime;
    private String departureGate;

    // arrival
    private String arrivalAirport;
    private Date arrivalDate1;
    private Time arrivalTime;
    private String arrivalGate;

    //
    private String longFlight1;


    private String flightClass;
    private String airlineCode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_order_id")
    private Customers customers;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "booking_fk", referencedColumnName = "bookingId", updatable = true, insertable = true)
    private List<Passenger> passengers;
}