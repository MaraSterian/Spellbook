package com.example.spellbook.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import com.example.spellbook.dto.RegistrationResponse;
import com.example.spellbook.dto.UserDTO;
import com.example.spellbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {
    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@Valid @RequestBody UserDTO userDto) {
        try {
            boolean registrationSuccess = userService.registerUser(userDto);

            if (registrationSuccess) {
                RegistrationResponse response = new RegistrationResponse(true, "User registration successful");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                RegistrationResponse response = new RegistrationResponse(false, "User registration unsuccessful");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (DataIntegrityViolationException e) {
            log.error("User registration failed", e);
            RegistrationResponse response = new RegistrationResponse(false, "User registration failed due to data integrity violation: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<RegistrationResponse> loginUser(@RequestBody UserDTO userDto) {
        try {
            UserDTO userExists = userService.getUserByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());

            if (userExists != null) {
                RegistrationResponse response = new RegistrationResponse(true, "User logged in successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                RegistrationResponse response = new RegistrationResponse(false, "Invalid login credentials");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error("User login failed", e);
            RegistrationResponse response = new RegistrationResponse(false, "User login failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<RegistrationResponse> logoutUser() {
        boolean logoutSuccess = userService.logoutUser();

        if (logoutSuccess) {
            RegistrationResponse response = new RegistrationResponse(true, "User logged out successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            RegistrationResponse response = new RegistrationResponse(false, "Logout unsuccessful");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
