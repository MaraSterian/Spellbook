package com.example.spellbook.mapper;

import com.example.spellbook.dto.MagicalTraditionDTO;
import com.example.spellbook.model.MagicalTradition;
import org.springframework.stereotype.Component;

@Component
public class MagicalTraditionMapper {
    public MagicalTraditionDTO magicalTraditionToMagicalTraditionDTO(MagicalTradition magicalTradition) {
        MagicalTraditionDTO magicalTraditionDTO = new MagicalTraditionDTO();
        magicalTraditionDTO.setId(magicalTradition.getId());
        magicalTraditionDTO.setName(magicalTradition.getName());
        magicalTraditionDTO.setDescription(magicalTradition.getDescription());
        return magicalTraditionDTO;
    }

    public MagicalTradition magicalTraditionDTOToMagicalTradition(MagicalTraditionDTO magicalTraditionDTO) {
        MagicalTradition magicalTradition = new MagicalTradition();
        magicalTradition.setId(magicalTraditionDTO.getId());
        magicalTradition.setName(magicalTraditionDTO.getName());
        magicalTradition.setDescription(magicalTraditionDTO.getDescription());
        return magicalTradition;
    }
}
