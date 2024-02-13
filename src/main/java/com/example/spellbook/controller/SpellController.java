package com.example.spellbook.controller;

import com.example.spellbook.dto.SpellDTO;
import com.example.spellbook.service.SpellService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spells")
public class SpellController {
    private final SpellService spellService;

    public SpellController(SpellService spellService) {
        this.spellService = spellService;
    }

    @GetMapping
    public ResponseEntity<List<SpellDTO>> getAllSpells() {
        List<SpellDTO> spells = spellService.getAllSpells();
        return new ResponseEntity<>(spells, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellDTO> getSpellById(@PathVariable Long id) {
        SpellDTO spell = spellService.getSpellById(id);
        return new ResponseEntity<>(spell, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createSpell(@RequestBody SpellDTO spellDTO) {
        spellService.createSpell(spellDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSpell(@PathVariable Long id, @RequestBody SpellDTO spellDTO) {
        spellService.updateSpell(id, spellDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpell(@PathVariable Long id) {
        spellService.deleteSpell(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
