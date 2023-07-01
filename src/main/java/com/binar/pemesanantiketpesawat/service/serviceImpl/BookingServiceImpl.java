package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.BookingRequest;
import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.model.NotificationMessage;
import com.binar.pemesanantiketpesawat.model.STATUS;
import com.binar.pemesanantiketpesawat.repository.AirlineRepository;
import com.binar.pemesanantiketpesawat.repository.BookingRepository;
import com.binar.pemesanantiketpesawat.service.BookingService;
import com.binar.pemesanantiketpesawat.service.DetailService;
import com.binar.pemesanantiketpesawat.service.FirebaseMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private DetailService detailService;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    @Autowired

    private String getDataForSavingIntoDatabase() {
        return null;
    }

    private String getRand() {
        String saltChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rand = new Random();
        while (salt.length() < 6) {
            int index = (int) (rand.nextFloat() * saltChar.length());
            salt.append(saltChar.charAt(index));
        }

        return salt.toString();
    }

    public Booking saveDataBooking(BookingRequest bookingRequest) {


        DetailFlight detailResponse = detailService.getDetailPenerbangan(bookingRequest.getAirlineCode(), bookingRequest.getFlightClass(), bookingRequest.getAdult(), bookingRequest.getChild(), 0);

        Airline airlineResponse = airlineRepository.findByAirlineCode(bookingRequest.getAirlineCode());

        String codeBooking = getRand().toUpperCase();

        Booking tempBooking = new Booking(
                0,
                bookingRequest.getUuidUser(),
                codeBooking,
                airlineResponse.getAirlineName(),
                detailResponse.getDepartureAirport(),
                detailResponse.getDepartureDate(),
                detailResponse.getDepartureTime(),
                airlineResponse.getDepartureGate(),
                detailResponse.getArrivalAirport(),
                detailResponse.getArrivalDate(),
                detailResponse.getArrivalTime(),
                airlineResponse.getArrivalGate(),
                detailResponse.getLongFlight(),
                STATUS.UNPAID,
                bookingRequest.getFlightClass(),
                bookingRequest.getAirlineCode(),
                bookingRequest.getAdult(),
                detailResponse.getAdultPrice(),
                bookingRequest.getChild(),
                detailResponse.getChildPrice(),
                bookingRequest.getBaby(),
                0,
                detailResponse.getTotalPrice(),
                bookingRequest.getCustomers(),
                bookingRequest.getPassengers()
        );


        NotificationMessage notificationMessage = new NotificationMessage(
                0,
                bookingRequest.getUuidUser(),
                "e4WW1J1RPp8BHIKjSP-djz:APA91bFZzEzQt41YhM7-INV-4Ssv0x1et_FoKhyE2Pr9VQCP8DrYXdVVQKQ4yIAKEFdy-YmkCcS0E5RHBwyIob4ZaPcFCmeBKGHf90LfVEU8yqi6ZJYN6WRlLpydsXubf2JD3iAhB5dk",
                "Booking",
                "Anda memiliki penerbangan dengan token " + codeBooking + " jangan lupa yaa",
                "https://www.seiu1000.org/sites/main/files/main-images/camera_lense_0.jpeg"
        );

        String tokenResponse = firebaseMessagingService.sendNotificationByToken(notificationMessage);

        return bookingRepository.save(tempBooking);
    }


    @Override
    public List<Booking> getAllPesanan() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> findByUuidUser(UUID uuidRequest) {
        return bookingRepository.findByUuidUser(uuidRequest);
    }

    @Override
    public Booking searchBookingByCode(String bookingCodeRequest) {
        return bookingRepository.findBookingByBookingCode(bookingCodeRequest);
    }
}
