package com.binar.pemesanantiketpesawat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column (name = "full_name")
    private String fullName;
    @Column (name = "family_name")
    private String familyName;
    @Column (name = "phone_number")
    private String phoneNumber;
    @Column (name = "email")
    private String email;
}