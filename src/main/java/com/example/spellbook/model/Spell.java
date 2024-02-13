package com.example.spellbook.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spells")
public class Spell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String ingredients;
    private String steps;
    private String outcome;

    @ManyToOne
    @JoinColumn(name = "spellbook_id")
    private Spellbook spellbook;

    @ManyToOne
    @JoinColumn(name = "magical_tradition_id")
    private MagicalTradition magicalTradition;
}


