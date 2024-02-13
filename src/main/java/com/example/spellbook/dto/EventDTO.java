package com.example.spellbook.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDTO {
    private int id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime date_and_time;
    private UserDTO user;
}
