package com.binar.pemesanantiketpesawat.request;

import lombok.Data;

@Data
public class OrderRequest {

    private String fullName;
    private String familyName;
    private String phoneNumber;
    private String email;
}
