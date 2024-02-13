package com.example.spellbook.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
