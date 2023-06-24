package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.service.InvoiceService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    private void getBookingTicket(@RequestParam String codeBookingRequest) throws JRException, FileNotFoundException {
        invoiceService.searchBookingCodeByCodeBooking(codeBookingRequest);
    }

}
