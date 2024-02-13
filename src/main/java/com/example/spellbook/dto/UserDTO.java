package com.example.spellbook.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class UserDTO {
    private int id;

    @NotNull(message = "Username cannot be empty")
    @Size(min = 2, max = 30, message = "Username must be between 2 and 30 characters long")
    private String username;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be empty")
    private String password;
}
