package com.example.spellbook.repository;

import com.example.spellbook.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.dateAndTime < :dateTime")
    List<Event> findByDateAndTimeBefore(LocalDateTime dateTime);
}
