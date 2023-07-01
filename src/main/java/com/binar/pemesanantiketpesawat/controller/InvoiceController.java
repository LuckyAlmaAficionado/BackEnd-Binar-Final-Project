package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.service.InvoiceService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {
    private static final Logger log = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{bookingCodeRequest}")
    private ResponseEntity<byte[]> generateInvoice(@PathVariable String bookingCodeRequest) throws JRException, IOException {
        log.info("Received request to generate invoice for booking code: {}", bookingCodeRequest);

        JasperPrint jasperPrint = invoiceService.generateInvoice(bookingCodeRequest);
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("invoice.pdf").build());

        log.info("Invoice generated successfully for booking code: {}", bookingCodeRequest);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
