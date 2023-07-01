package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Customers;
import com.binar.pemesanantiketpesawat.service.CustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customers")
public class CustomersController {
    private static final Logger log = LoggerFactory.getLogger(CustomersController.class);

    @Autowired
    private CustomersService customersService;

    @PostMapping
    public ResponseEntity<MessageModel> saveCustomers(@RequestBody Customers customersRequest) {
        log.info("Received request to save customers");

        MessageModel messageModel = new MessageModel();
        Customers customersResponse = customersService.saveCustomers(customersRequest);

        if (customersResponse == null) {
            log.info("Failed to save customers");

            messageModel.setMessage("Failed to save");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(messageModel);
        } else {
            log.info("Customers saved successfully");

            messageModel.setMessage("Success save data");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(customersResponse);

            return ResponseEntity.ok(messageModel);
        }
    }

    @PutMapping
    public ResponseEntity<MessageModel> updateCustomer(@RequestBody Customers customersRequest) {
        log.info("Received request to update customers");

        MessageModel messageModel = new MessageModel();
        Customers customersResponse = customersService.updateCustomersById(customersRequest);

        if (customersResponse == null) {
            log.info("Failed to update customers");

            messageModel.setMessage("Failed to update");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(messageModel);
        } else {
            log.info("Customers updated successfully");

            messageModel.setMessage("Success update data");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(customersResponse);

            return ResponseEntity.ok(messageModel);
        }
    }

    @DeleteMapping
    public ResponseEntity<MessageModel> deleteCustomers(@RequestParam Integer idRequest) {
        log.info("Received request to delete customers");

        MessageModel messageModel = new MessageModel();
        String customersResponse = customersService.deleteCustomers(idRequest);

        if (customersResponse == null) {
            log.info("Failed to delete customers");

            messageModel.setMessage("Failed to delete customers");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(messageModel);
        } else {
            log.info("Customers deleted successfully");

            messageModel.setMessage("Success delete data");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(customersResponse);

            return ResponseEntity.ok(messageModel);
        }
    }

    @GetMapping
    public ResponseEntity<MessageModel> getCustomersById(@RequestParam Integer idRequest) {
        log.info("Received request to get customers by ID");

        MessageModel messageModel = new MessageModel();
        Customers customersResponse = customersService.findByIdCustomers(idRequest);

        if (customersResponse == null) {
            log.info("Failed to get customers by ID");

            messageModel.setMessage("Failed to get customer by ID: " + idRequest);
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(messageModel);
        } else {
            log.info("Customers retrieved successfully");

            messageModel.setMessage("Success retrieve data");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(customersResponse);

            return ResponseEntity.ok(messageModel);
        }
    }
}
