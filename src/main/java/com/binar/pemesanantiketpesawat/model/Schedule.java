package com.binar.pemesanantiketpesawat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Integer timeId;
    @Column(name = "continent_category", nullable = false)
    private String continentCategory;
    @Column(name = "favorite_flight", nullable = false)
    private Boolean favoriteFlight;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "departure_date", nullable = false)
    private Date departureDate;
    @Column(name = "departure_city", nullable = false)
    private String departureCity;
    @Column(name = "departure_airport", nullable = false)
    private String departureAirport;
    @Column(name = "arrival_city", nullable = false)
    private String arrivalCity;
    @Column(name = "arrival_airport", nullable = false)
    private String arrivalAirport;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "departure_date_fk", referencedColumnName = "time_id", insertable = true, updatable = true)
    private List<Time> schedulesList;
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @JsonIgnore
    @Column(name = "modified_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    public Schedule(Integer timeId, Boolean favoriteFlight, Date departureDate, String departureCity, String arrivalCity, List<Time> schedulesList) {
        this.timeId = timeId;
        this.favoriteFlight = favoriteFlight;
        this.departureDate = departureDate;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.schedulesList = schedulesList;
    }

    public Schedule(String continentCategory, Boolean favoriteFlight, Date departureDate, String departureCity, String departureAirport, String arrivalCity, String arrivalAirport) {
        this.continentCategory = continentCategory;
        this.favoriteFlight = favoriteFlight;
        this.departureDate = departureDate;
        this.departureCity = departureCity;
        this.departureAirport = departureAirport;
        this.arrivalCity = arrivalCity;
        this.arrivalAirport = arrivalAirport;
    }
}
