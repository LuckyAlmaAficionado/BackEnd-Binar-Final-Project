package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.BookingRequest;
import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.repository.AirlineRepository;
import com.binar.pemesanantiketpesawat.repository.BookingRepository;
import com.binar.pemesanantiketpesawat.service.BookingService;
import com.binar.pemesanantiketpesawat.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private DetailService detailService;
    @Autowired
    private AirlineRepository airlineRepository;

    private String getDataForSavingIntoDatabase() {
        return null;
    }

    public Booking saveDataBooking(BookingRequest bookingRequest) {


        DetailFlight detailResponse = detailService.getDetailPenerbanganByCodeRequestAndClassResponse(bookingRequest.getAirlineCode(), bookingRequest.getFlightClass());

        Airline airlineResponse = airlineRepository.findByAirlineCode(bookingRequest.getAirlineCode());

        Booking tempBooking = new Booking(
                0,
                "A1342S",
                detailResponse.getDepartureAirport(),
                detailResponse.getDepartureDate(),
                detailResponse.getDepartureTime(),
                airlineResponse.getDepartureGate(),
                detailResponse.getArrivalAirport(),
                detailResponse.getArrivalDate(),
                detailResponse.getArrivalTime(),
                airlineResponse.getArrivalGate(),
                detailResponse.getLongFlight(),
                bookingRequest.getFlightClass(),
                bookingRequest.getAirlineCode(),
                bookingRequest.getCustomers(),
                bookingRequest.getPassengers()
        );

        return bookingRepository.save(tempBooking);
    }

    @Override
    public List<Booking> getAllPesanan() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking searchBookingByCode(String bookingCodeRequest) {
        return bookingRepository.findBookingByBookingCode(bookingCodeRequest);
    }
}
