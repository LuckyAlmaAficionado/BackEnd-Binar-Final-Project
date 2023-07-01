package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.Payload.Request.LoginRequest;
import com.binar.pemesanantiketpesawat.Payload.Request.SignupRequest;
import com.binar.pemesanantiketpesawat.Payload.Response.MessageResponse;
import com.binar.pemesanantiketpesawat.dto.UserRequestUpdate;
import com.binar.pemesanantiketpesawat.repository.RoleRepository;
import com.binar.pemesanantiketpesawat.repository.UserRepository;
import com.binar.pemesanantiketpesawat.request.CommonResponse;
import com.binar.pemesanantiketpesawat.request.CommonResponseGenerator;
import com.binar.pemesanantiketpesawat.request.Token;
import com.binar.pemesanantiketpesawat.security.JWT.AuthenticationResponse;
import com.binar.pemesanantiketpesawat.security.JWT.JwtUtils;
import com.binar.pemesanantiketpesawat.security.Service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthService authService;

    @Autowired
    private CommonResponseGenerator crg;

    private Token token = new Token();

    @PostMapping("/signin")
    public CommonResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Received request to authenticate user");

        AuthenticationResponse authenticationResponse = authService.authenticateUser(loginRequest);

        log.info("User authentication successful");

        token.setToken(authenticationResponse.getJwt());
        token.setUuidUser(authenticationResponse.getUuidUser());

        return crg.successResponse(token);
    }

    @PostMapping("/signup")
    public CommonResponse registerBuyer(@RequestBody SignupRequest signupRequest) {
        log.info("Received request to register user");

        authService.registerUser(signupRequest);
        AuthenticationResponse authenticationResponse = authService.authenticateUser(new LoginRequest(signupRequest.getEmail(), signupRequest.getPassword()));
        token.setToken(authenticationResponse.getJwt());

        log.info("User registration and authentication successful");

        return crg.successResponse(token);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        log.info("Received request to logout user");

        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();

        log.info("User logout successful");

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new MessageResponse("You've been signed out!"));
    }

    @PutMapping("/update")
    public String updatePersonalData(@Valid @RequestBody UserRequestUpdate userRequest) {
        log.info("Received request to update user personal data");

        String result = authService.updatePersonalData(userRequest);

        log.info("User personal data update successful");

        return result;
    }
}
