package com.binar.pemesanantiketpesawat.service;

import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public interface InvoiceService {

    void searchBookingCodeByCodeBooking(HttpServletResponse response, String codeBooking) throws JRException, IOException;
}
