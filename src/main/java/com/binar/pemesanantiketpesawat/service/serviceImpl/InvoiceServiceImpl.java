package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.InvoiceModel;
import com.binar.pemesanantiketpesawat.dto.InvoiceModelRequest;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.model.Passenger;
import com.binar.pemesanantiketpesawat.repository.AirportRepository;
import com.binar.pemesanantiketpesawat.repository.BookingRepository;
import com.binar.pemesanantiketpesawat.service.AirlineService;
import com.binar.pemesanantiketpesawat.service.InvoiceService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private AirlineService airlineService;
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public void searchBookingCodeByCodeBooking(String codeBookingRequest) throws JRException, FileNotFoundException {
        Booking bookingResponse = bookingRepository.findBookingByBookingCode(codeBookingRequest);

        Airline airlineResponse = airlineService.searchByAirlineCode(bookingResponse.getAirlineCode());

        Integer baggage = airlineResponse.getFlightClass().get(0).getAirlineBaggage();

        InvoiceModelRequest invoiceTempData = new InvoiceModelRequest(
                codeBookingRequest,
                "" + bookingResponse.getDepartureDate(),//
                airlineResponse.getAirlineName(),//
                airlineResponse.getAirlineCode(),//
                bookingResponse.getFlightClass(),//
                "" + bookingResponse.getDepartureTime(),//
                "" + bookingResponse.getArrivalTime(),//
                "" + airportRepository.findByAirportLocation(bookingResponse.getDepartureAirport()).getAirportName(), //
                "" + airportRepository.findByAirportLocation(bookingResponse.getArrivalAirport()).getAirportName(), //
                bookingResponse.getDepartureAirport(),//
                bookingResponse.getArrivalAirport(),//
                bookingResponse.getDepartureGate(),//
                bookingResponse.getArrivalGate(), //
                bookingResponse.getLongFlight1()//
        );

        Collection<InvoiceModel> modelCollections = new ArrayList<>();
        for (Passenger passenger : bookingResponse.getPassengers()) {
            modelCollections.add(new InvoiceModel(
                    passenger.getTitle(),
                    passenger.getFullName(),
                    "dewasa",
                    codeBookingRequest,
                    bookingResponse.getAirlineCode(),
                    "Baggage " + baggage + " Kg"
            ));
        }

        jasperReport(modelCollections, invoiceTempData);
    }


    public void jasperReport(
            Collection<InvoiceModel> invoiceModelsRequest,
            InvoiceModelRequest modelRequest
    ) throws JRException, FileNotFoundException {
        String savePath = "E:\\code\\.springboot\\.challenge\\pemesanan-tiket-pesawat\\jasper-report\\src\\main\\resources\\static\\invoice.pdf";

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoiceModelsRequest);


        Map<String, Object> param = new HashMap<>();
        param.put("bookingCode", modelRequest.getBookingCode());
        param.put("flightDate", modelRequest.getFlightDate());
        param.put("airlineName", modelRequest.getAirlineName());
        param.put("airlineCode", modelRequest.getAirlineCode());
        param.put("flightClass", modelRequest.getFlightClass());
        param.put("departureTime",modelRequest.getDepartureTime());
        param.put("arrivalTime", modelRequest.getArrivalTime());
        param.put("departureAirport", modelRequest.getDepartureAirport());
        param.put("arrivalAirport", modelRequest.getArrivalAirport());
        param.put("departureCity", modelRequest.getDepartureCity());
        param.put("arrivalCity", modelRequest.getArrivalCity());
        param.put("departureGate", modelRequest.getDepartureGate());
        param.put("arrivalGate", modelRequest.getArrivalGate());
        param.put("longFlight", modelRequest.getLongFlight());

        File filePath = ResourceUtils.getFile("classpath:FlightInvoice.jrxml");
        JasperReport report = JasperCompileManager.compileReport(filePath.getAbsolutePath());
        JasperPrint print = JasperFillManager.fillReport(report, param, dataSource);
        JasperExportManager.exportReportToPdfFile(print, savePath);
        System.out.println("Report Generator Successfully...!");
    }
}
