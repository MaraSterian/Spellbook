package com.example.spellbook.controller;

import com.example.spellbook.dto.SpellbookDTO;
import com.example.spellbook.service.SpellbookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spellbooks")
public class SpellbookController {
    private final SpellbookService spellbookService;

    public SpellbookController(SpellbookService spellbookService) {
        this.spellbookService = spellbookService;
    }

    @GetMapping
    public ResponseEntity<List<SpellbookDTO>> getAllSpellbooks() {
        List<SpellbookDTO> spellbooks = spellbookService.getAllSpellbooks();
        return new ResponseEntity<>(spellbooks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellbookDTO> getSpellbookById(@PathVariable Long id) {
        SpellbookDTO spellbook = spellbookService.getSpellbookById(id);
        return new ResponseEntity<>(spellbook, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createSpellbook(@RequestBody SpellbookDTO spellbookDTO) {
        spellbookService.createSpellbook(spellbookDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSpellbook(@PathVariable Long id, @RequestBody SpellbookDTO spellbookDTO) {
        spellbookService.updateSpellbook(id, spellbookDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpellbook(@PathVariable Long id) {
        spellbookService.deleteSpellbook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
