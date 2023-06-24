package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.model.Booking;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public interface InvoiceService {

    void searchBookingCodeByCodeBooking(String codeBooking) throws JRException, FileNotFoundException;
}
