package com.example.spellbook.service;

import com.example.spellbook.dto.SpellbookDTO;
import com.example.spellbook.mapper.SpellbookMapper;
import com.example.spellbook.model.Spellbook;
import com.example.spellbook.repository.SpellbookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpellbookService {
    private final SpellbookRepository spellbookRepository;
    private final SpellbookMapper spellbookMapper;

    @Autowired
    public SpellbookService(SpellbookRepository spellbookRepository, SpellbookMapper spellbookMapper) {
        this.spellbookRepository = spellbookRepository;
        this.spellbookMapper = spellbookMapper;
    }

    public List<SpellbookDTO> getAllSpellbooks() {
        List<Spellbook> spellbooks = spellbookRepository.findAll();
        return spellbooks.stream()
                .map(spellbookMapper::spellbookToSpellbookDTO)
                .collect(Collectors.toList());
    }

    public SpellbookDTO getSpellbookById(Long spellbookId) {
        Spellbook spellbook = spellbookRepository.findById(spellbookId)
                .orElseThrow(() -> new EntityNotFoundException("Spellbook not found"));

        return spellbookMapper.spellbookToSpellbookDTO(spellbook);
    }

    public void createSpellbook(SpellbookDTO spellbookDTO) {
        Spellbook spellbook = spellbookMapper.spellbookDTOToSpellbook(spellbookDTO);
        spellbookRepository.save(spellbook);
    }

    public void updateSpellbook(Long spellbookId, SpellbookDTO spellbookDTO) {
        Spellbook existingSpellbook = spellbookRepository.findById(spellbookId)
                .orElseThrow(() -> new EntityNotFoundException("Spellbook not found"));

        // Update the existingEvent based on eventDTO
        existingSpellbook.setName(spellbookDTO.getName());
        existingSpellbook.setDescription(spellbookDTO.getDescription());

        spellbookRepository.save(existingSpellbook);
    }

    public void deleteSpellbook(Long spellbookId) {
        spellbookRepository.deleteById(spellbookId);
    }
}
