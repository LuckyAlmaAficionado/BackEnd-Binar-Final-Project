package com.binar.pemesanantiketpesawat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String fullName;
    private String familyName;
    private String phoneNumber;
    private String email;

}
