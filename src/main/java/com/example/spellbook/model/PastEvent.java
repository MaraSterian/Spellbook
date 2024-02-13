package com.example.spellbook.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "past_events")
public class PastEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private LocalDateTime date_and_time;
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
