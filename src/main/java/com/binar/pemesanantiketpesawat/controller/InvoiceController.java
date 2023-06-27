package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.service.InvoiceService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{codeBookingRequest}")
    private void getBookingTicket(HttpServletResponse response, @PathVariable String codeBookingRequest) throws JRException, IOException {

        invoiceService.searchBookingCodeByCodeBooking(response, codeBookingRequest);
    }

}
