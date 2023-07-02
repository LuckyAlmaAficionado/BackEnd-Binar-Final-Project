package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewRole() {
        log.info("Received request to add new role");

        String newRole = roleService.createNewRole();

        log.info("New role added: {}", newRole);

        return newRole;
    }
}
