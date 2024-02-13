package com.example.spellbook.controller;

import com.example.spellbook.dto.MagicalTraditionDTO;
import com.example.spellbook.service.MagicalTraditionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/magical-traditions")
public class MagicalTraditionController {
    private final MagicalTraditionService magicalTraditionService;

    public MagicalTraditionController(MagicalTraditionService magicalTraditionService) {
        this.magicalTraditionService = magicalTraditionService;
    }

    @GetMapping
    public ResponseEntity<List<MagicalTraditionDTO>> getAllMagicalTraditions() {
        List<MagicalTraditionDTO> magicalTraditions = magicalTraditionService.getAllMagicalTraditions();
        return new ResponseEntity<>(magicalTraditions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MagicalTraditionDTO> getMagicalTraditionById(@PathVariable Long id) {
        MagicalTraditionDTO magicalTradition = magicalTraditionService.getMagicalTraditionById(id);
        return new ResponseEntity<>(magicalTradition, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createMagicalTradition(@RequestBody MagicalTraditionDTO magicalTraditionDTO) {
        magicalTraditionService.createMagicalTradition(magicalTraditionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMagicalTradition(@PathVariable Long id, @RequestBody MagicalTraditionDTO magicalTraditionDTO) {
        magicalTraditionService.updateMagicalTradition(id, magicalTraditionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMagicalTradition(@PathVariable Long id) {
        magicalTraditionService.deleteMagicalTradition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
