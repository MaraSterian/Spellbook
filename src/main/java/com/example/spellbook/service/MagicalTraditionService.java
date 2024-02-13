package com.example.spellbook.service;

import com.example.spellbook.dto.MagicalTraditionDTO;
import com.example.spellbook.mapper.MagicalTraditionMapper;
import com.example.spellbook.model.MagicalTradition;
import com.example.spellbook.repository.MagicalTraditionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MagicalTraditionService {
    private final MagicalTraditionRepository magicalTraditionRepository;
    private final MagicalTraditionMapper magicalTraditionMapper;
    private static final Logger log = LoggerFactory.getLogger(MagicalTraditionService.class);

    @Autowired
    public MagicalTraditionService(MagicalTraditionRepository magicalTraditionRepository, MagicalTraditionMapper magicalTraditionMapper) {
        this.magicalTraditionRepository = magicalTraditionRepository;
        this.magicalTraditionMapper = magicalTraditionMapper;
    }

    public List<MagicalTraditionDTO> getAllMagicalTraditions() {
        List<MagicalTradition> magicalTraditions = magicalTraditionRepository.findAll();
        return magicalTraditions.stream()
                .map(magicalTraditionMapper::magicalTraditionToMagicalTraditionDTO)
                .collect(Collectors.toList());
    }

    public MagicalTraditionDTO getMagicalTraditionById(Long magicalTraditionId) {
        MagicalTradition magicalTradition = magicalTraditionRepository.findById(magicalTraditionId)
                .orElseThrow(() -> new EntityNotFoundException("Magical Tradition not found"));

        return magicalTraditionMapper.magicalTraditionToMagicalTraditionDTO(magicalTradition);
    }

    public void createMagicalTradition(MagicalTraditionDTO magicalTraditionDTO) {
        MagicalTradition magicalTradition = magicalTraditionMapper.magicalTraditionDTOToMagicalTradition(magicalTraditionDTO);
        magicalTraditionRepository.save(magicalTradition);
        log.info("Created new Magical Tradition: {}", magicalTraditionDTO.getName());
    }

    public void updateMagicalTradition(Long magicalTraditionId, MagicalTraditionDTO magicalTraditionDTO) {
        MagicalTradition existingMagicalTradition = magicalTraditionRepository.findById(magicalTraditionId)
                .orElseThrow(() -> new EntityNotFoundException("Magical Tradition not found"));

        // Update the existingMagicalTradition based on magicalTraditionDTO
        existingMagicalTradition.setName(magicalTraditionDTO.getName());
        existingMagicalTradition.setDescription(magicalTraditionDTO.getDescription());

        magicalTraditionRepository.save(existingMagicalTradition);
        log.info("Updated Magical Tradition with ID {}: {}", magicalTraditionId, magicalTraditionDTO.getName());
    }

    public void deleteMagicalTradition(Long magicalTraditionId) {
        magicalTraditionRepository.deleteById(magicalTraditionId);
        log.info("Deleted Magical Tradition with ID: {}", magicalTraditionId);
    }
}
