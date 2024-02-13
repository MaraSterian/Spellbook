package com.example.spellbook.dto;
import lombok.Getter;

@Getter
public class RegistrationResponse {
    private final boolean success;
    private final String message;

    public RegistrationResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
