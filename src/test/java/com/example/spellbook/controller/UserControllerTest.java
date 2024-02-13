package com.example.spellbook.controller;

import com.example.spellbook.dto.RegistrationResponse;
import com.example.spellbook.dto.UserDTO;
import com.example.spellbook.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testRegisterUserSuccess() {
        UserDTO userDto = new UserDTO();
        when(userService.registerUser(userDto)).thenReturn(true);

        ResponseEntity<RegistrationResponse> responseEntity = userController.registerUser(userDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("User registration successful", responseEntity.getBody().getMessage());
    }

    @Test
    public void testRegisterUserFailure() {
        UserDTO userDto = new UserDTO();
        when(userService.registerUser(userDto)).thenReturn(false);

        ResponseEntity<RegistrationResponse> responseEntity = userController.registerUser(userDto);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User registration unsuccessful", responseEntity.getBody().getMessage());
    }

    @Test
    public void testRegisterUserException() {
        UserDTO userDto = new UserDTO();
        when(userService.registerUser(userDto)).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Some error"));

        ResponseEntity<RegistrationResponse> responseEntity = userController.registerUser(userDto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("User registration failed: Some error", responseEntity.getBody().getMessage());
    }

    @Test
    public void testLoginUserSuccess() {
        UserDTO userDto = new UserDTO();
        when(userService.getUserByUsernameAndPassword("username", "password")).thenReturn(userDto);

        ResponseEntity<RegistrationResponse> responseEntity = userController.loginUser(userDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User logged in successfully", responseEntity.getBody().getMessage());
    }

    @Test
    public void testLoginUserFailure() {
        UserDTO userDto = new UserDTO();
        when(userService.getUserByUsernameAndPassword("username", "password")).thenReturn(null);

        ResponseEntity<RegistrationResponse> responseEntity = userController.loginUser(userDto);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid login credentials", responseEntity.getBody().getMessage());
    }

    @Test
    public void testLoginUserException() {
        UserDTO userDto = new UserDTO();
        when(userService.getUserByUsernameAndPassword("username", "password")).thenThrow(new RuntimeException("Some error"));

        ResponseEntity<RegistrationResponse> responseEntity = userController.loginUser(userDto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("User login failed: Some error", responseEntity.getBody().getMessage());
    }

    @Test
    public void testLogoutUserSuccess() {
        when(userService.logoutUser()).thenReturn(true);

        ResponseEntity<RegistrationResponse> responseEntity = userController.logoutUser();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User logged out successfully", responseEntity.getBody().getMessage());
    }

    @Test
    public void testLogoutUserFailure() {
        when(userService.logoutUser()).thenReturn(false);

        ResponseEntity<RegistrationResponse> responseEntity = userController.logoutUser();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Logout unsuccessful", responseEntity.getBody().getMessage());
    }
}

