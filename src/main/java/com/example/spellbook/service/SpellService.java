package com.example.spellbook.service;

import com.example.spellbook.dto.SpellDTO;
import com.example.spellbook.mapper.SpellMapper;
import com.example.spellbook.model.Spell;
import com.example.spellbook.repository.SpellRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpellService {
    private final SpellRepository spellRepository;
    private final SpellMapper spellMapper;

    @Autowired
    public SpellService(SpellRepository spellRepository, SpellMapper spellMapper) {
        this.spellRepository = spellRepository;
        this.spellMapper = spellMapper;
    }

    public List<SpellDTO> getAllSpells() {
        List<Spell> spells = spellRepository.findAll();
        return spells.stream()
                .map(spellMapper::spellToSpellDTO)
                .collect(Collectors.toList());
    }

    public SpellDTO getSpellById(Long spellId) {
        Spell spell = spellRepository.findById(spellId)
                .orElseThrow(() -> new EntityNotFoundException("Spell not found"));

        return spellMapper.spellToSpellDTO(spell);
    }

    public void createSpell(SpellDTO spellDTO) {
        Spell spell = spellMapper.spellDTOToSpell(spellDTO);
        spellRepository.save(spell);
    }

    public void updateSpell(Long spellId, SpellDTO spellDTO) {
        Spell existingSpell = spellRepository.findById(spellId)
                .orElseThrow(() -> new EntityNotFoundException("Spell not found"));

        // Update the existingEvent based on eventDTO
        existingSpell.setName(spellDTO.getName());
        existingSpell.setDescription(spellDTO.getDescription());
        existingSpell.setIngredients(spellDTO.getIngredients());
        existingSpell.setSteps(spellDTO.getSteps());
        existingSpell.setOutcome(spellDTO.getOutcome());

        spellRepository.save(existingSpell);
    }

    public void deleteSpell(Long spellId) {
        spellRepository.deleteById(spellId);
    }
}
