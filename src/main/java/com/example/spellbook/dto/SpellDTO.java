package com.example.spellbook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpellDTO {
    private int id;
    private String name;
    private String description;
    private String ingredients;
    private String steps;
    private String outcome;

    private SpellbookDTO spellbook;
    private MagicalTraditionDTO magicalTradition;
}
