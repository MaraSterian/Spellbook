package com.example.spellbook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpellbookDTO {
    private int id;
    private String name;
    private String description;
    private UserDTO user;
    //private List<SpellDTO> spells;
}
