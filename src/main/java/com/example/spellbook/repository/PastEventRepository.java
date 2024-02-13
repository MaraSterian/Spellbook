package com.example.spellbook.repository;

import com.example.spellbook.model.PastEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastEventRepository extends JpaRepository<PastEvent, Long>  {

}
