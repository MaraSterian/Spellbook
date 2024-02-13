package com.example.spellbook.mapper;

import com.example.spellbook.dto.SpellDTO;
import com.example.spellbook.model.Spell;
import org.springframework.stereotype.Component;

@Component
public class SpellMapper {
    SpellbookMapper spellbookMapper;
    MagicalTraditionMapper magicalTraditionMapper;
    public SpellDTO spellToSpellDTO(Spell spell) {
        SpellDTO spellDTO = new SpellDTO();
        spellDTO.setId(spell.getId());
        spellDTO.setName(spell.getName());
        spellDTO.setDescription(spell.getDescription());
        spellDTO.setIngredients(spell.getIngredients());
        spellDTO.setSteps(spell.getSteps());
        spellDTO.setOutcome(spell.getOutcome());
        if (spell.getSpellbook() != null) {
            spellDTO.setSpellbook(spellbookMapper.spellbookToSpellbookDTO(spell.getSpellbook()));
        }
        if (spell.getMagicalTradition() != null) {
            spellDTO.setMagicalTradition(magicalTraditionMapper.magicalTraditionToMagicalTraditionDTO(spell.getMagicalTradition()));
        }
        return spellDTO;
    }

    public Spell spellDTOToSpell(SpellDTO spellDTO) {
        Spell spell = new Spell();
        spell.setId(spellDTO.getId());
        spell.setName(spellDTO.getName());
        spell.setDescription(spellDTO.getDescription());
        spell.setIngredients(spellDTO.getIngredients());
        spell.setSteps(spellDTO.getSteps());
        spell.setOutcome(spellDTO.getOutcome());
        if (spellDTO.getSpellbook() != null) {
            spell.setSpellbook(spellbookMapper.spellbookDTOToSpellbook(spellDTO.getSpellbook()));
        }
        if (spellDTO.getMagicalTradition() != null) {
            spell.setMagicalTradition(magicalTraditionMapper.magicalTraditionDTOToMagicalTradition(spellDTO.getMagicalTradition()));
        }
        return spell;
    }
}
