package com.example.spellbook.mapper;

import com.example.spellbook.dto.SpellbookDTO;
import com.example.spellbook.model.Spellbook;
import org.springframework.stereotype.Component;

@Component
public class SpellbookMapper {
    public SpellbookDTO spellbookToSpellbookDTO(Spellbook spellbook) {
        SpellbookDTO spellbookDTO = new SpellbookDTO();
        spellbookDTO.setId(spellbook.getId());
        spellbookDTO.setName(spellbook.getName());
        spellbookDTO.setDescription(spellbook.getDescription());
        spellbookDTO.setUser(UserMapper.userToUserDTO(spellbook.getUser()));
        return spellbookDTO;
    }

    public Spellbook spellbookDTOToSpellbook(SpellbookDTO spellbookDTO) {
        Spellbook spellbook = new Spellbook();
        spellbook.setId(spellbookDTO.getId());
        spellbook.setName(spellbookDTO.getName());
        spellbook.setDescription(spellbookDTO.getDescription());
        spellbook.setUser(UserMapper.userDTOToUser(spellbookDTO.getUser()));
        return spellbook;
    }
}
