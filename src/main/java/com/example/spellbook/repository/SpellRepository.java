package com.example.spellbook.repository;

import com.example.spellbook.model.Spell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellRepository extends JpaRepository<Spell, Long> {
}
