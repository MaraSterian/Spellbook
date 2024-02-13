package com.example.spellbook.repository;

import com.example.spellbook.model.Spellbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellbookRepository extends JpaRepository<Spellbook, Long> {

}
