package com.example.spellbook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private int id;
    private UserDTO user;
    private SpellDTO spell;
    private String content;
}
