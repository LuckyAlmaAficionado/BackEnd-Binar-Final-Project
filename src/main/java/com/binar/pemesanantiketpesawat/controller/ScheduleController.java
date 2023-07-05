package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.DetailFlightList;
import com.binar.pemesanantiketpesawat.dto.FavoriteFlightModel;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.dto.ScheduleRequest;
import com.binar.pemesanantiketpesawat.model.Schedule;
import com.binar.pemesanantiketpesawat.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;
    @GetMapping("/lol")
    public String lol(){
        return "masuk";
    }
    @GetMapping("/favorite-destination")
    ResponseEntity<MessageModel> findByFavoriteDestination() {
        MessageModel messageModel = new MessageModel();
        List<FavoriteFlightModel> scheduleResponse = scheduleService.findByFavoriteDestination();
        if (scheduleResponse == null) {
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("no data found");
            logger.warn("No data found for favorite destinations");
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("managed to get the data");
            messageModel.setData(scheduleResponse);
            logger.info("Successfully retrieved favorite destinations");
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/get-all")
    private ResponseEntity<MessageModel> getAllSchedules() {
        MessageModel messageModel = new MessageModel();
        try {
            List<Schedule> schedulesResponse = scheduleService.getAllSchedules();
            messageModel.setMessage("Success get all schedules");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(schedulesResponse);
            logger.info("Successfully retrieved all schedules");
            return ResponseEntity.ok().body(messageModel);
        } catch (Exception e) {
            messageModel.setMessage("Failed get schedules");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            logger.error("Failed to retrieve schedules", e);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping("/search")
    private ResponseEntity<MessageModel> getAirplaneScheduleTicket(
            @RequestParam("depDate") Date departureDate,
            @RequestParam("depAirport") String departureAirport,
            @RequestParam("arrAirport") String arrivalAirport,
            @RequestParam("seatClass") String seatClass) {
        MessageModel messageModel = new MessageModel();
        try {
            List<Schedule> scheduleResponse =
                    scheduleService.searchAirplaneTicketSchedule(
                            departureDate, departureAirport, arrivalAirport, seatClass);
            messageModel.setMessage("Success get schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponse);
            logger.info("Successfully retrieved airplane ticket schedule");
            return ResponseEntity.ok().body(messageModel);
        } catch (Exception exception) {
            messageModel.setMessage("Failed get schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            logger.error("Failed to retrieve airplane ticket schedule", exception);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping("/search-price-asc")
    private ResponseEntity<MessageModel> getAirplanePriceAsc(
            @RequestParam("depDate") Date departureDate,
            @RequestParam("depAirport") String departureAirport,
            @RequestParam("arrAirport") String arrivalAirport,
            @RequestParam("seatClass") String seatClass) {
        MessageModel messageModel = new MessageModel();
        List<DetailFlightList> scheduleResponse =
                scheduleService.filterDataPriceAsc(
                        departureDate, departureAirport, arrivalAirport, seatClass);
        try {
            messageModel.setMessage("Success get schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponse);
            logger.info("Successfully retrieved airplane ticket schedule with price ascending order");
            return ResponseEntity.ok().body(messageModel);
        } catch (Exception e) {
            messageModel.setMessage("Failed get schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            logger.error("Failed to retrieve airplane ticket schedule with price ascending order", e);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping("/search-price-desc")
    private ResponseEntity<MessageModel> getAirplanePriceDesc(
            @RequestParam("depDate") Date departureDate,
            @RequestParam("depAirport") String departureAirport,
            @RequestParam("arrAirport") String arrivalAirport,
            @RequestParam("seatClass") String seatClass) {
        MessageModel messageModel = new MessageModel();
        List<DetailFlightList> scheduleResponse =
                scheduleService.filterDataPriceDesc(
                        departureDate, departureAirport, arrivalAirport, seatClass);
        try {
            messageModel.setMessage("Success get schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponse);
            logger.info("Successfully retrieved airplane ticket schedule with price descending order");
            return ResponseEntity.ok().body(messageModel);
        } catch (Exception e) {
            messageModel.setMessage("Failed get schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            logger.error("Failed to retrieve airplane ticket schedule with price descending order", e);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping("/search-schedule")
    private ResponseEntity<MessageModel> getAirplaneWithSchedule(
            @RequestParam("depDate") Date departureDate,
            @RequestParam("depAirport") String departureAirport,
            @RequestParam("arrAirport") String arrivalAirport,
            @RequestParam("seatClass") String seatClass) {
        MessageModel messageModel = new MessageModel();
        try {
            List<DetailFlightList> scheduleResponse =
                    scheduleService.filterDataSchedule(
                            departureDate, departureAirport, arrivalAirport, seatClass);
            messageModel.setMessage("Success get schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponse);
            logger.info("Successfully retrieved airplane ticket schedule with detailed flight information");
            return ResponseEntity.ok().body(messageModel);
        } catch (Exception exception) {
            messageModel.setMessage("Failed get schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            logger.error("Failed to retrieve airplane ticket schedule with detailed flight information", exception);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @PostMapping("/add-schedule")
    private ResponseEntity<MessageModel> addSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        MessageModel messageModel = new MessageModel();
        Schedule scheduleResponse = scheduleService.addSchedule(scheduleRequest);
        if (scheduleResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add schedule");
            logger.error("Failed to add new schedule");
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Add new schedule");
            messageModel.setData(scheduleResponse);
            logger.info("Successfully added new schedule");
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @DeleteMapping("delete-schedule")
    public ResponseEntity<MessageModel> deleteAllSchedules() {
        MessageModel messageModel = new MessageModel();
        scheduleService.deleteAllAirplaneTicketSchedule();
        messageModel.setStatus(HttpStatus.OK.value());
        messageModel.setMessage("Successfully delete all data...");
        logger.info("Successfully deleted all schedules");
        return ResponseEntity.ok().body(messageModel);
    }

    @PutMapping("update-schedule")
    public ResponseEntity<MessageModel> updateSchedule(@RequestBody Schedule scheduleRequest) {
        MessageModel messageModel = new MessageModel();
        Schedule scheduleResponse = scheduleService.updateSchedule(scheduleRequest);
        messageModel.setData(scheduleResponse);
        logger.info("Successfully updated schedule");
        return ResponseEntity.ok(messageModel);
    }

    @GetMapping("/duration/{departureTime}/{arrivalTime}")
    public String getFlightDuration(@PathVariable String departureTime, @PathVariable String arrivalTime) {
        return scheduleService.calculateFlightDuration(departureTime, arrivalTime);
    }
}
